package foundation.icon.btp.mock;

import foundation.icon.btp.lib.BSH;
import foundation.icon.jsonrpc.Address;
import foundation.icon.jsonrpc.IconStringConverter;
import foundation.icon.jsonrpc.model.TransactionResult;
import foundation.icon.score.client.DefaultScoreClient;
import foundation.icon.score.client.Wallet;
import java.lang.Deprecated;
import java.lang.Object;
import java.lang.RuntimeException;
import java.lang.String;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.function.Consumer;
import java.util.function.Predicate;

public final class MockBSHScoreClient extends DefaultScoreClient implements BSH, MockBSH {
  public MockBSHScoreClient(String url, BigInteger nid, Wallet wallet, Address address) {
    super(url, nid, wallet, address);
  }

  public MockBSHScoreClient(String url, BigInteger nid, BigInteger stepLimit, Wallet wallet,
      Address address) {
    super(url, nid, stepLimit, wallet, address);
  }

  public MockBSHScoreClient(DefaultScoreClient client) {
    super(client);
  }

  public MockBSHScoreClient(DefaultScoreClient client, Wallet wallet) {
    super(client, wallet);
  }

  public static MockBSHScoreClient _of(Properties properties) {
    return _of("", properties);
  }

  public static MockBSHScoreClient _of(String prefix, Properties properties) {
    return new MockBSHScoreClient(DefaultScoreClient.of(prefix, properties));
  }

  public void handleBTPError(String arg0, String arg1, BigInteger arg2, long arg3, String arg4) {
    Map<String,Object> params = new HashMap<>();
    params.put("arg0",arg0);
    params.put("arg1",arg1);
    params.put("arg2",arg2);
    params.put("arg3",arg3);
    params.put("arg4",arg4);
    super._send("handleBTPError", params);
  }

  public void handleBTPError(Consumer<TransactionResult> consumerFunc, String arg0, String arg1,
      BigInteger arg2, long arg3, String arg4) {
    Map<String,Object> params = new HashMap<>();
    params.put("arg0",arg0);
    params.put("arg1",arg1);
    params.put("arg2",arg2);
    params.put("arg3",arg3);
    params.put("arg4",arg4);
    consumerFunc.accept(super._send("handleBTPError", params));
  }

  public void handleBTPMessage(String arg0, String arg1, BigInteger arg2, byte[] arg3) {
    Map<String,Object> params = new HashMap<>();
    params.put("arg0",arg0);
    params.put("arg1",arg1);
    params.put("arg2",arg2);
    params.put("arg3",arg3);
    super._send("handleBTPMessage", params);
  }

  public void handleBTPMessage(Consumer<TransactionResult> consumerFunc, String arg0, String arg1,
      BigInteger arg2, byte[] arg3) {
    Map<String,Object> params = new HashMap<>();
    params.put("arg0",arg0);
    params.put("arg1",arg1);
    params.put("arg2",arg2);
    params.put("arg3",arg3);
    consumerFunc.accept(super._send("handleBTPMessage", params));
  }

  /**
   * To payable, use sendMessage(BigInteger valueForPayable, ...)
   */
  public void sendMessage(score.Address _bmc, String _to, String _svc, BigInteger _sn,
      byte[] _msg) {
    Map<String,Object> params = new HashMap<>();
    params.put("_bmc",_bmc);
    params.put("_to",_to);
    params.put("_svc",_svc);
    params.put("_sn",_sn);
    params.put("_msg",_msg);
    super._send("sendMessage", params);
  }

  public void sendMessage(Consumer<TransactionResult> consumerFunc, score.Address _bmc, String _to,
      String _svc, BigInteger _sn, byte[] _msg) {
    Map<String,Object> params = new HashMap<>();
    params.put("_bmc",_bmc);
    params.put("_to",_to);
    params.put("_svc",_svc);
    params.put("_sn",_sn);
    params.put("_msg",_msg);
    consumerFunc.accept(super._send("sendMessage", params));
  }

  public void sendMessage(BigInteger valueForPayable, score.Address _bmc, String _to, String _svc,
      BigInteger _sn, byte[] _msg) {
    Map<String,Object> params = new HashMap<>();
    params.put("_bmc",_bmc);
    params.put("_to",_to);
    params.put("_svc",_svc);
    params.put("_sn",_sn);
    params.put("_msg",_msg);
    super._send(valueForPayable, "sendMessage", params);
  }

  public void sendMessage(Consumer<TransactionResult> consumerFunc, BigInteger valueForPayable,
      score.Address _bmc, String _to, String _svc, BigInteger _sn, byte[] _msg) {
    Map<String,Object> params = new HashMap<>();
    params.put("_bmc",_bmc);
    params.put("_to",_to);
    params.put("_svc",_svc);
    params.put("_sn",_sn);
    params.put("_msg",_msg);
    consumerFunc.accept(super._send(valueForPayable, "sendMessage", params));
  }

  /**
   * @deprecated Do not use this method, this is generated only for preventing compile error.
   *  Instead, use N/A
   * @throws java.lang.RuntimeException("not supported EventLog method")
   */
  @Deprecated
  public void SendMessage(BigInteger _nsn, String _to, String _svc, BigInteger _sn, byte[] _msg) {
    throw new RuntimeException("not supported EventLog method");
  }

  public Consumer<TransactionResult> SendMessage(Consumer<List<SendMessage>> consumerFunc,
      Predicate<SendMessage> filter) {
    return (txr) -> consumerFunc.accept(SendMessage.eventLogs(txr, this.address, filter));
  }

  /**
   * @deprecated Do not use this method, this is generated only for preventing compile error.
   *  Instead, use N/A
   * @throws java.lang.RuntimeException("not supported EventLog method")
   */
  @Deprecated
  public void HandleBTPMessage(String _from, String _svc, BigInteger _sn, byte[] _msg) {
    throw new RuntimeException("not supported EventLog method");
  }

  public Consumer<TransactionResult> HandleBTPMessage(Consumer<List<HandleBTPMessage>> consumerFunc,
      Predicate<HandleBTPMessage> filter) {
    return (txr) -> consumerFunc.accept(HandleBTPMessage.eventLogs(txr, this.address, filter));
  }

  /**
   * @deprecated Do not use this method, this is generated only for preventing compile error.
   *  Instead, use N/A
   * @throws java.lang.RuntimeException("not supported EventLog method")
   */
  @Deprecated
  public void HandleBTPError(String _src, String _svc, BigInteger _sn, long _code, String _msg) {
    throw new RuntimeException("not supported EventLog method");
  }

  public Consumer<TransactionResult> HandleBTPError(Consumer<List<HandleBTPError>> consumerFunc,
      Predicate<HandleBTPError> filter) {
    return (txr) -> consumerFunc.accept(HandleBTPError.eventLogs(txr, this.address, filter));
  }

  public static MockBSHScoreClient _deploy(String url, BigInteger nid, Wallet wallet,
      String scoreFilePath, Map<String, Object> params) {
    return new MockBSHScoreClient(DefaultScoreClient._deploy(url,nid,wallet,scoreFilePath,params));
  }

  public static MockBSHScoreClient _of(String prefix, Properties properties,
      Map<String, Object> params) {
    return new MockBSHScoreClient(DefaultScoreClient.of(prefix, properties, params));
  }

  public static class SendMessage {
    public static final String SIGNATURE = "SendMessage(int,str,str,int,bytes)";

    public static final int INDEXED = 1;

    private final BigInteger _nsn;

    private final String _to;

    private final String _svc;

    private final BigInteger _sn;

    private final byte[] _msg;

    public SendMessage(TransactionResult.EventLog el) {
      List<String> indexed = el.getIndexed();
      List<String> data = el.getData();
      this._nsn=IconStringConverter.toBigInteger(indexed.get(1));
      this._to=data.get(0);
      this._svc=data.get(1);
      this._sn=IconStringConverter.toBigInteger(data.get(2));
      this._msg=IconStringConverter.toBytes(data.get(3));
    }

    public BigInteger get_nsn() {
      return this._nsn;
    }

    public String get_to() {
      return this._to;
    }

    public String get_svc() {
      return this._svc;
    }

    public BigInteger get_sn() {
      return this._sn;
    }

    public byte[] get_msg() {
      return this._msg;
    }

    public static List<SendMessage> eventLogs(TransactionResult txr, Address address,
        Predicate<SendMessage> filter) {
      return DefaultScoreClient.eventLogs(txr, SIGNATURE, address, SendMessage::new, filter);
    }
  }

  public static class HandleBTPMessage {
    public static final String SIGNATURE = "HandleBTPMessage(str,str,int,bytes)";

    public static final int INDEXED = 0;

    private final String _from;

    private final String _svc;

    private final BigInteger _sn;

    private final byte[] _msg;

    public HandleBTPMessage(TransactionResult.EventLog el) {
      List<String> indexed = el.getIndexed();
      List<String> data = el.getData();
      this._from=data.get(0);
      this._svc=data.get(1);
      this._sn=IconStringConverter.toBigInteger(data.get(2));
      this._msg=IconStringConverter.toBytes(data.get(3));
    }

    public String get_from() {
      return this._from;
    }

    public String get_svc() {
      return this._svc;
    }

    public BigInteger get_sn() {
      return this._sn;
    }

    public byte[] get_msg() {
      return this._msg;
    }

    public static List<HandleBTPMessage> eventLogs(TransactionResult txr, Address address,
        Predicate<HandleBTPMessage> filter) {
      return DefaultScoreClient.eventLogs(txr, SIGNATURE, address, HandleBTPMessage::new, filter);
    }
  }

  public static class HandleBTPError {
    public static final String SIGNATURE = "HandleBTPError(str,str,int,int,str)";

    public static final int INDEXED = 0;

    private final String _src;

    private final String _svc;

    private final BigInteger _sn;

    private final long _code;

    private final String _msg;

    public HandleBTPError(TransactionResult.EventLog el) {
      List<String> indexed = el.getIndexed();
      List<String> data = el.getData();
      this._src=data.get(0);
      this._svc=data.get(1);
      this._sn=IconStringConverter.toBigInteger(data.get(2));
      this._code=IconStringConverter.toBigInteger(data.get(3)).longValue();
      this._msg=data.get(4);
    }

    public String get_src() {
      return this._src;
    }

    public String get_svc() {
      return this._svc;
    }

    public BigInteger get_sn() {
      return this._sn;
    }

    public long get_code() {
      return this._code;
    }

    public String get_msg() {
      return this._msg;
    }

    public static List<HandleBTPError> eventLogs(TransactionResult txr, Address address,
        Predicate<HandleBTPError> filter) {
      return DefaultScoreClient.eventLogs(txr, SIGNATURE, address, HandleBTPError::new, filter);
    }
  }
}
