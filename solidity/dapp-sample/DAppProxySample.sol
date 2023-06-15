// SPDX-License-Identifier: Apache-2.0
pragma solidity >=0.8.0;
pragma abicoder v2;

import "../xcall/interfaces/ICallService.sol";
import "../xcall/interfaces/ICallServiceReceiver.sol";
import "./RLPEncode.sol";

import "@openzeppelin/contracts-upgradeable/proxy/utils/Initializable.sol";
import "@openzeppelin/contracts/utils/Strings.sol";
import "solidity-rlp/contracts/RLPReader.sol";

contract DAppProxySample is ICallServiceReceiver, Initializable {
    address private callSvc;
    string private callSvcBtpAddr;
    uint256 private lastId;
    uint8 internal constant LIST_SHORT_START = 0xc0;
    uint8 internal constant LIST_LONG_START = 0xf7;

    using RLPEncode for bytes;
    using RLPEncode for string;
    using RLPEncode for uint256;
    using RLPEncode for int256;
    using RLPEncode for address;
    using RLPEncode for bool;

    struct RollbackData {
        uint256 id;
        bytes rollback;
        uint256 ssn;
    }
    mapping(uint256 => RollbackData) private rollbacks;

    struct MessageData {
        uint256 id;
        string message;
        uint256 offset;
        uint256 length;
    }

    mapping(uint256 => MessageData) private messagess;
    mapping(bytes32 => MessageData) private message;

    modifier onlyCallService() {
        require(msg.sender == callSvc, "OnlyCallService");
        _;
    }

    function initialize(address _callService) public initializer {
        callSvc = _callService;
        callSvcBtpAddr = ICallService(callSvc).getBtpAddress();
    }

    function compareTo(
        string memory _base,
        string memory _value
    ) internal pure returns (bool) {
        if (
            keccak256(abi.encodePacked(_base)) ==
            keccak256(abi.encodePacked(_value))
        ) {
            return true;
        }
        return false;
    }

    function sendMessage(
        string calldata _to,
        uint256 _type,
        bytes calldata _data,
        bytes calldata _rollback
    ) external payable {
        uint256 value = msg.value;
        bytes memory rollback_msg = _rollback;
        if (_type == 0) {
            string memory msgData = string(_data);
            uint256 id = ++lastId;
            MessageData memory msg_data = MessageData(id, msgData, 0, 0);
            // bytes memory encodedMsg = abi.encode(msg_data);
            // bytes memory encodedMsg = encodeMessageData(msg_data);
            bytes memory encodedMsg = encodeCSMessageRequest(msg_data);
            messageSend(value, id, _to, encodedMsg, _rollback);
        } else if (_type == 1) {
            string memory msgData = string(_data);
            string[] memory data_chunks = splitByLength(msgData, 7);
            uint256 i = 0;
            string memory add = _to;
            uint256 fees = value / data_chunks.length;
            while (i < data_chunks.length) {
                uint256 id = ++lastId;
                string memory data = data_chunks[i];
                MessageData memory msg_data = MessageData(
                    id,
                    data,
                    i,
                    data_chunks.length
                );

                bytes memory encodedMsg = encodeCSMessageRequest(msg_data);
                if (i == data_chunks.length - 1) {
                    messageSend(fees, id, add, encodedMsg, rollback_msg);
                } else {
                    messageSend(fees, id, add, encodedMsg, "");
                }
                i++;
            }
        } else {
            uint256 id = ++lastId;
            bytes memory data = abi.encode(_data);
            // bytes memory encodedMsg = encodeMessageData(_data);
            messageSend(value, id, _to, data, _rollback);
        }
    }

    function messageSend(
        uint256 value,
        uint256 id,
        string memory to,
        bytes memory data,
        bytes memory _rollback
    ) private {
        if (_rollback.length > 0) {
            bytes memory encodedRd = abi.encode(id, _rollback);
            uint256 sn = ICallService(callSvc).sendCallMessage{value: value}(
                to,
                data,
                encodedRd
            );
            rollbacks[id] = RollbackData(id, _rollback, sn);
        } else {
            ICallService(callSvc).sendCallMessage{value: value}(
                to,
                data,
                _rollback
            );
        }
    }

    function splitByLength(
        string memory _str,
        uint256 _chunkSize
    ) internal pure returns (string[] memory) {
        bytes memory strBytes = bytes(_str);
        uint256 numChunks = strBytes.length / _chunkSize;
        if (strBytes.length % _chunkSize != 0) {
            numChunks++;
        }

        string[] memory chunks = new string[](numChunks);
        uint256 chunkIndex = 0;

        for (uint256 i = 0; i < strBytes.length; i += _chunkSize) {
            uint256 chunkLength = _chunkSize;
            if (i + chunkLength > strBytes.length) {
                chunkLength = strBytes.length - i;
            }

            bytes memory chunkBytes = new bytes(chunkLength);

            for (uint256 j = 0; j < chunkLength; j++) {
                chunkBytes[j] = strBytes[i + j];
            }

            chunks[chunkIndex] = string(chunkBytes);
            chunkIndex++;
        }

        return chunks;
    }

    /**
       @notice Handles the call message received from the source chain.
       @dev Only called from the Call Message Service.
       @param _from The BTP address of the caller on the source chain
       @param _data The calldata delivered from the caller
     */
    function handleCallMessage(
        string calldata _from,
        bytes calldata _data
    ) external override onlyCallService {
        if (compareTo(_from, callSvcBtpAddr)) {
            // handle rollback data here
            // In this example, just compare it with the stored one.
            (uint256 id, bytes memory received) = abi.decode(
                _data,
                (uint256, bytes)
            );
            RollbackData memory stored = rollbacks[id];
            require(
                compareTo(string(received), string(stored.rollback)),
                "rollbackData mismatch"
            );
            delete rollbacks[id]; // cleanup
            emit RollbackDataReceived(_from, stored.ssn, received);
        } else {
            // normal message delivery
            // string memory msgData = string(_data);
            MessageData memory msg_data = decoderlp(_data);

            if (msg_data.length > 0) {
                if (msg_data.length - 1 == msg_data.offset) {
                    string memory msgData;
                    for (uint256 i = 0; i < msg_data.length - 1; i++) {
                        uint256 key = msg_data.id - msg_data.offset;
                        MessageData memory stored = messagess[key + i];
                        msgData = concatenate(msgData, stored.message);
                    }
                    msgData = concatenate(msgData, msg_data.message);
                    if (compareTo("revertMessage", msgData)) {
                        revert("revertFromDApp");
                    }

                    bytes memory msg_data_bytes = bytes(msgData);
                    emit MessageReceived(_from, msg_data_bytes);
                } else {
                    messagess[msg_data.id] = msg_data;
                    // bytes memory msg_data_bytes11 = bytes(msg_data.message);
                    // emit MessageReceived(_from, msg_data_bytes11);
                }
            } else {
                bytes memory msg_data_bytes = bytes(msg_data.message);
                if (compareTo("revertMessage", msg_data.message)) {
                    revert("revertFromDApp");
                }
                emit MessageReceived(_from, msg_data_bytes);
            }
        }
    }

    using RLPReader for RLPReader.RLPItem;
    using RLPReader for RLPReader.Iterator;
    using RLPReader for bytes;

    function decoderlp(
        bytes memory rlpBytes
    ) public pure returns (MessageData memory) {
        RLPReader.RLPItem[] memory ls = rlpBytes.toRlpItem().toList(); // must convert to an rlpItem first!

        return
            MessageData(
                ls[0].toUint(),
                string(ls[1].toBytes()),
                ls[2].toUint(),
                ls[3].toUint()
            );
    }

    function encodeCSMessageRequest(
        MessageData memory data
    ) internal pure returns (bytes memory) {
        bytes memory _rlp = abi.encodePacked(
            RLPEncode.encodeUint(data.id),
            RLPEncode.encodeString(data.message),
            RLPEncode.encodeUint(data.offset),
            RLPEncode.encodeUint(data.length)
        );
        return abi.encodePacked(addLength(_rlp.length, false), _rlp);
    }

    function concatenate(
        string memory a,
        string memory b
    ) public pure returns (string memory) {
        return string(abi.encodePacked(a, "", b));
    }

    function addLength(
        uint256 length,
        bool isLongList
    ) internal pure returns (bytes memory) {
        if (length > 55 && !isLongList) {
            bytes memory payLoadSize = RLPEncode.encodeUintByLength(length);
            return
                abi.encodePacked(
                    addLength(payLoadSize.length, true),
                    payLoadSize
                );
        } else if (length <= 55 && !isLongList) {
            return abi.encodePacked(uint8(LIST_SHORT_START + length));
        }
        return abi.encodePacked(uint8(LIST_LONG_START + length));
    }

    event MessageReceived(string _from, bytes _data);

    event MessageReceivedData(string _from, string data);

    event RollbackDataReceived(string _from, uint256 _ssn, bytes _rollback);
}
