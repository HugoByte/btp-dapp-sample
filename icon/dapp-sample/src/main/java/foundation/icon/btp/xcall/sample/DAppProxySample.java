/*
 * Copyright 2022 ICON Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package foundation.icon.btp.xcall.sample;

import foundation.icon.btp.xcall.CallServiceReceiver;
import foundation.icon.score.client.ScoreClient;
import score.Address;
import score.Context;
import score.DictDB;
import score.UserRevertedException;
import score.VarDB;
import score.annotation.EventLog;
import score.annotation.External;
import score.annotation.Optional;
import score.annotation.Payable;

import java.math.BigInteger;

@ScoreClient
public class DAppProxySample implements CallServiceReceiver {
    private final XCallProxy xCall;
    private final String callSvcBtpAddr;
    private final VarDB<BigInteger> id = Context.newVarDB("id", BigInteger.class);
    private final DictDB<BigInteger, RollbackData> rollbacks = Context.newDictDB("rollbacks", RollbackData.class);

    private final DictDB<BigInteger, MessageData> messages = Context.newDictDB("messages", MessageData.class);

    public DAppProxySample(Address _callService) {
        this.xCall = new XCallProxy(_callService);
        this.callSvcBtpAddr = xCall.getBtpAddress();
    }

    private void onlyCallService() {
        Context.require(Context.getCaller().equals(xCall.address()), "onlyCallService");
    }

    private BigInteger getNextId() {
        BigInteger _id = this.id.getOrDefault(BigInteger.ZERO);
        _id = _id.add(BigInteger.ONE);
        this.id.set(_id);
        return _id;
    }

    @Payable
    @External
    public void sendMessage(String _to, BigInteger _type, byte[] _data, @Optional byte[] _rollback) {
        var id = getNextId();
        BigInteger feess = Context.getValue();
        if (_type.intValue() == 0) {
            String msgData = new String(_data);
            MessageData msg = new MessageData(id, msgData, 0, 0);
            byte[] msg_data = msg.toBytes();
            messageSend(feess, id, _to, msg_data, _rollback);

        } else if (_type.intValue() == 1) {
            int chunkSize = 7;
            String msgData = new String(_data);
            String[] data_chunks = splitByLength(msgData, chunkSize);

            int i = 0;
            while (i < data_chunks.length) {
                String data = data_chunks[i];

                MessageData msg = new MessageData(id, data, i, data_chunks.length);
                BigInteger feee = feess.divide(BigInteger.valueOf(data_chunks.length));
                byte[] msg_data = msg.toBytes();
                if (i == data_chunks.length - 1) {
                    messageSend(feee, id, _to, msg_data, _rollback);
                } else {
                    messageSend(feee, id, _to, msg_data, null);
                    id = getNextId();
                }
                i++;
            }

        }

    }

    private void messageSend(BigInteger value, BigInteger id, String to, byte[] data, byte[] _rollback) {
        if (_rollback != null) {
            // The code below is not actually necessary because the _rollback data is stored
            // on the xCall side,
            // but in this example, it is needed for testing to compare the _rollback data
            // later.

            Context.println("DAppProxy: store rollback data with id=" + id);
            RollbackData rbData = new RollbackData(id, _rollback);
            var ssn = _sendCallMessage(value, to, data, rbData.toBytes());
            rbData.setSvcSn(ssn);
            rollbacks.set(id, rbData);
        } else {
            // This is for one-way message
            _sendCallMessage(value, to, data, null);
        }

    }

    public static String[] splitByLength(String inputString, int substringLength) {
        int length = inputString.length();
        int substringCount = length / substringLength + (length % substringLength == 0 ? 0 : 1);
        String[] substrings = new String[substringCount];

        int startIndex = 0;
        int endIndex = substringLength;

        for (int i = 0; i < substringCount; i++) {
            if (endIndex > length) {
                endIndex = length;
            }
            substrings[i] = inputString.substring(startIndex, endIndex);

            startIndex = endIndex;
            endIndex += substringLength;
        }

        return substrings;
    }

    private BigInteger _sendCallMessage(BigInteger value, String to, byte[] data, byte[] rollback) {
        try {
            return xCall.sendCallMessage(value, to, data, rollback);
        } catch (UserRevertedException e) {
            // propagate the error code to the caller
            Context.revert(e.getCode(), "UserReverted");
            return BigInteger.ZERO; // call flow does not reach here, but make compiler happy
        }
    }

    @Override
    @External
    public void handleCallMessage(String _from, byte[] _data) {
        onlyCallService();
        Context.println("handleCallMessage: from=" + _from);
        if (callSvcBtpAddr.equals(_from)) {
            // handle rollback data here
            // In this example, just compare it with the stored one.
            RollbackData received = RollbackData.fromBytes(_data);
            var id = received.getId();
            RollbackData stored = rollbacks.get(id);
            Context.require(stored != null, "invalid received id");
            Context.require(received.equals(stored), "rollbackData mismatch");
            rollbacks.set(id, null); // cleanup
            RollbackDataReceived(_from, stored.getSvcSn(), received.getRollback());
        } else {
            // normal message delivery
            MessageData msgData = MessageData.fromBytes(_data);
            if (msgData.getLength() > 0) {
                if (msgData.getLength() - 1 == msgData.getOffset()) {
                    String msg = "";
                    for (int i = 0; i < msgData.getLength() - 1; i++) {
                        BigInteger keys = BigInteger.valueOf(msgData.getId().intValue() - msgData.getOffset() + i);
                        MessageData stored = messages.get(keys);
                        msg = msg.concat(stored.getMessage());
                    }
                    msg = msg.concat(msgData.getMessage());
                    Context.println("handleCallMessage: msgData= " + msg);
                    if ("revertMessage".equals(msg)) {
                        Context.revert("revertFromDApp");
                    }
                    MessageReceived(_from, msg.getBytes());
                } else {
                    messages.set(msgData.getId(), msgData);
                }
            } else {
                Context.println("handleCallMessage: msgData= " + msgData.getMessage());
                if ("revertMessage".equals(msgData.getMessage())) {
                    Context.revert("revertFromDApp");
                }
                MessageReceived(_from, msgData.getMessage().getBytes());
            }

        }
    }

    @EventLog
    public void MessageReceived(String _from, byte[] _data) {
    }

    @EventLog
    public void MessageEend(String _from) {
    }

    @EventLog
    public void RollbackDataReceived(String _from, BigInteger _ssn, byte[] _rollback) {
    }
}
