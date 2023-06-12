package foundation.icon.btp.mock;

import foundation.icon.jsonrpc.Address;
import foundation.icon.jsonrpc.IconStringConverter;
import foundation.icon.jsonrpc.model.TransactionResult;
import foundation.icon.score.client.DefaultScoreClient;
import foundation.icon.score.client.Wallet;
import java.lang.Boolean;
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

public final class MockBMCScoreClient extends DefaultScoreClient implements MockBMC {
  public MockBMCScoreClient(String url, BigInteger nid, Wallet wallet, Address address) {
    super(url, nid, wallet, address);
  }

  public MockBMCScoreClient(String url, BigInteger nid, BigInteger stepLimit, Wallet wallet,
      Address address) {
    super(url, nid, stepLimit, wallet, address);
  }

  public MockBMCScoreClient(DefaultScoreClient client) {
    super(client);
  }

  public MockBMCScoreClient(DefaultScoreClient client, Wallet wallet) {
    super(client, wallet);
  }

  public static MockBMCScoreClient _of(Properties properties) {
    return _of("", properties);
  }

  public static MockBMCScoreClient _of(String prefix, Properties properties) {
    return new MockBMCScoreClient(DefaultScoreClient.of(prefix, properties));
  }

  public void setNetworkAddress(String _net) {
    Map<String,Object> params = new HashMap<>();
    params.put("_net",_net);
    super._send("setNetworkAddress", params);
  }

  public void setNetworkAddress(Consumer<TransactionResult> consumerFunc, String _net) {
    Map<String,Object> params = new HashMap<>();
    params.put("_net",_net);
    consumerFunc.accept(super._send("setNetworkAddress", params));
  }

  public String getNetworkAddress() {
    return super._call(String.class, "getNetworkAddress", null);
  }

  public void handleRelayMessage(score.Address _addr, String _prev, BigInteger _seq, byte[] _msg) {
    Map<String,Object> params = new HashMap<>();
    params.put("_addr",_addr);
    params.put("_prev",_prev);
    params.put("_seq",_seq);
    params.put("_msg",_msg);
    super._send("handleRelayMessage", params);
  }

  public void handleRelayMessage(Consumer<TransactionResult> consumerFunc, score.Address _addr,
      String _prev, BigInteger _seq, byte[] _msg) {
    Map<String,Object> params = new HashMap<>();
    params.put("_addr",_addr);
    params.put("_prev",_prev);
    params.put("_seq",_seq);
    params.put("_msg",_msg);
    consumerFunc.accept(super._send("handleRelayMessage", params));
  }

  /**
   * @deprecated Do not use this method, this is generated only for preventing compile error.
   *  Instead, use N/A
   * @throws java.lang.RuntimeException("not supported EventLog method")
   */
  @Deprecated
  public void HandleRelayMessage(byte[] _ret) {
    throw new RuntimeException("not supported EventLog method");
  }

  public Consumer<TransactionResult> HandleRelayMessage(
      Consumer<List<HandleRelayMessage>> consumerFunc, Predicate<HandleRelayMessage> filter) {
    return (txr) -> consumerFunc.accept(HandleRelayMessage.eventLogs(txr, this.address, filter));
  }

  public String getBtpAddress() {
    return super._call(String.class, "getBtpAddress", null);
  }

  /**
   * @deprecated Do not use this method, this is generated only for preventing compile error.
   *  Instead, use sendMessage(Consumer<TransactionResult> consumerFunc, ...)
   * @throws java.lang.RuntimeException("not supported response of writable method in ScoreClient")
   */
  @Deprecated
  public BigInteger sendMessage(String _to, String _svc, BigInteger _sn, byte[] _msg) {
    throw new RuntimeException("not supported response of writable method in ScoreClient");
  }

  public void sendMessage(Consumer<TransactionResult> consumerFunc, String _to, String _svc,
      BigInteger _sn, byte[] _msg) {
    Map<String,Object> params = new HashMap<>();
    params.put("_to",_to);
    params.put("_svc",_svc);
    params.put("_sn",_sn);
    params.put("_msg",_msg);
    consumerFunc.accept(super._send("sendMessage", params));
  }

  public void sendMessage(BigInteger valueForPayable, String _to, String _svc, BigInteger _sn,
      byte[] _msg) {
    Map<String,Object> params = new HashMap<>();
    params.put("_to",_to);
    params.put("_svc",_svc);
    params.put("_sn",_sn);
    params.put("_msg",_msg);
    super._send(valueForPayable, "sendMessage", params);
  }

  public void sendMessage(Consumer<TransactionResult> consumerFunc, BigInteger valueForPayable,
      String _to, String _svc, BigInteger _sn, byte[] _msg) {
    Map<String,Object> params = new HashMap<>();
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

  public BigInteger getNetworkSn() {
    return super._call(BigInteger.class, "getNetworkSn", null);
  }

  public void addResponse(String _to, String _svc, BigInteger _sn) {
    Map<String,Object> params = new HashMap<>();
    params.put("_to",_to);
    params.put("_svc",_svc);
    params.put("_sn",_sn);
    super._send("addResponse", params);
  }

  public void addResponse(Consumer<TransactionResult> consumerFunc, String _to, String _svc,
      BigInteger _sn) {
    Map<String,Object> params = new HashMap<>();
    params.put("_to",_to);
    params.put("_svc",_svc);
    params.put("_sn",_sn);
    consumerFunc.accept(super._send("addResponse", params));
  }

  public boolean hasResponse(String _to, String _svc, BigInteger _sn) {
    Map<String,Object> params = new HashMap<>();
    params.put("_to",_to);
    params.put("_svc",_svc);
    params.put("_sn",_sn);
    return super._call(Boolean.class, "hasResponse", params);
  }

  public void clearResponse() {
    super._send("clearResponse", null);
  }

  public void clearResponse(Consumer<TransactionResult> consumerFunc) {
    consumerFunc.accept(super._send("clearResponse", null));
  }

  public void setFee(BigInteger _forward, BigInteger _backward) {
    Map<String,Object> params = new HashMap<>();
    params.put("_forward",_forward);
    params.put("_backward",_backward);
    super._send("setFee", params);
  }

  public void setFee(Consumer<TransactionResult> consumerFunc, BigInteger _forward,
      BigInteger _backward) {
    Map<String,Object> params = new HashMap<>();
    params.put("_forward",_forward);
    params.put("_backward",_backward);
    consumerFunc.accept(super._send("setFee", params));
  }

  public BigInteger getFee(String _to, boolean _response) {
    Map<String,Object> params = new HashMap<>();
    params.put("_to",_to);
    params.put("_response",_response);
    return super._call(BigInteger.class, "getFee", params);
  }

  public void handleBTPMessage(score.Address _addr, String _from, String _svc, BigInteger _sn,
      byte[] _msg) {
    Map<String,Object> params = new HashMap<>();
    params.put("_addr",_addr);
    params.put("_from",_from);
    params.put("_svc",_svc);
    params.put("_sn",_sn);
    params.put("_msg",_msg);
    super._send("handleBTPMessage", params);
  }

  public void handleBTPMessage(Consumer<TransactionResult> consumerFunc, score.Address _addr,
      String _from, String _svc, BigInteger _sn, byte[] _msg) {
    Map<String,Object> params = new HashMap<>();
    params.put("_addr",_addr);
    params.put("_from",_from);
    params.put("_svc",_svc);
    params.put("_sn",_sn);
    params.put("_msg",_msg);
    consumerFunc.accept(super._send("handleBTPMessage", params));
  }

  public void handleBTPError(score.Address _addr, String _src, String _svc, BigInteger _sn,
      long _code, String _msg) {
    Map<String,Object> params = new HashMap<>();
    params.put("_addr",_addr);
    params.put("_src",_src);
    params.put("_svc",_svc);
    params.put("_sn",_sn);
    params.put("_code",_code);
    params.put("_msg",_msg);
    super._send("handleBTPError", params);
  }

  public void handleBTPError(Consumer<TransactionResult> consumerFunc, score.Address _addr,
      String _src, String _svc, BigInteger _sn, long _code, String _msg) {
    Map<String,Object> params = new HashMap<>();
    params.put("_addr",_addr);
    params.put("_src",_src);
    params.put("_svc",_svc);
    params.put("_sn",_sn);
    params.put("_code",_code);
    params.put("_msg",_msg);
    consumerFunc.accept(super._send("handleBTPError", params));
  }

  public static MockBMCScoreClient _deploy(String url, BigInteger nid, Wallet wallet,
      String scoreFilePath, Map<String, Object> params) {
    return new MockBMCScoreClient(DefaultScoreClient._deploy(url,nid,wallet,scoreFilePath,params));
  }

  public static MockBMCScoreClient _of(String prefix, Properties properties,
      Map<String, Object> params) {
    return new MockBMCScoreClient(DefaultScoreClient.of(prefix, properties, params));
  }

  public static class HandleRelayMessage {
    public static final String SIGNATURE = "HandleRelayMessage(bytes)";

    public static final int INDEXED = 0;

    private final byte[] _ret;

    public HandleRelayMessage(TransactionResult.EventLog el) {
      List<String> indexed = el.getIndexed();
      List<String> data = el.getData();
      this._ret=IconStringConverter.toBytes(data.get(0));
    }

    public byte[] get_ret() {
      return this._ret;
    }

    public static List<HandleRelayMessage> eventLogs(TransactionResult txr, Address address,
        Predicate<HandleRelayMessage> filter) {
      return DefaultScoreClient.eventLogs(txr, SIGNATURE, address, HandleRelayMessage::new, filter);
    }
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
}
