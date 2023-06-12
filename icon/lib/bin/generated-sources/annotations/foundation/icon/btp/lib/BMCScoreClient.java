package foundation.icon.btp.lib;

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

public final class BMCScoreClient extends DefaultScoreClient implements BMC {
  public BMCScoreClient(String url, BigInteger nid, Wallet wallet, Address address) {
    super(url, nid, wallet, address);
  }

  public BMCScoreClient(String url, BigInteger nid, BigInteger stepLimit, Wallet wallet,
      Address address) {
    super(url, nid, stepLimit, wallet, address);
  }

  public BMCScoreClient(DefaultScoreClient client) {
    super(client);
  }

  public BMCScoreClient(DefaultScoreClient client, Wallet wallet) {
    super(client, wallet);
  }

  public static BMCScoreClient _of(Properties properties) {
    return _of("", properties);
  }

  public static BMCScoreClient _of(String prefix, Properties properties) {
    return new BMCScoreClient(DefaultScoreClient.of(prefix, properties));
  }

  public void addVerifier(String _net, score.Address _addr) {
    Map<String,Object> params = new HashMap<>();
    params.put("_net",_net);
    params.put("_addr",_addr);
    super._send("addVerifier", params);
  }

  public void addVerifier(Consumer<TransactionResult> consumerFunc, String _net,
      score.Address _addr) {
    Map<String,Object> params = new HashMap<>();
    params.put("_net",_net);
    params.put("_addr",_addr);
    consumerFunc.accept(super._send("addVerifier", params));
  }

  public void removeVerifier(String _net) {
    Map<String,Object> params = new HashMap<>();
    params.put("_net",_net);
    super._send("removeVerifier", params);
  }

  public void removeVerifier(Consumer<TransactionResult> consumerFunc, String _net) {
    Map<String,Object> params = new HashMap<>();
    params.put("_net",_net);
    consumerFunc.accept(super._send("removeVerifier", params));
  }

  public Map getVerifiers() {
    return super._call(Map.class, "getVerifiers", null);
  }

  public void addService(String _svc, score.Address _addr) {
    Map<String,Object> params = new HashMap<>();
    params.put("_svc",_svc);
    params.put("_addr",_addr);
    super._send("addService", params);
  }

  public void addService(Consumer<TransactionResult> consumerFunc, String _svc,
      score.Address _addr) {
    Map<String,Object> params = new HashMap<>();
    params.put("_svc",_svc);
    params.put("_addr",_addr);
    consumerFunc.accept(super._send("addService", params));
  }

  public void removeService(String _svc) {
    Map<String,Object> params = new HashMap<>();
    params.put("_svc",_svc);
    super._send("removeService", params);
  }

  public void removeService(Consumer<TransactionResult> consumerFunc, String _svc) {
    Map<String,Object> params = new HashMap<>();
    params.put("_svc",_svc);
    consumerFunc.accept(super._send("removeService", params));
  }

  public Map getServices() {
    return super._call(Map.class, "getServices", null);
  }

  public void addLink(String _link) {
    Map<String,Object> params = new HashMap<>();
    params.put("_link",_link);
    super._send("addLink", params);
  }

  public void addLink(Consumer<TransactionResult> consumerFunc, String _link) {
    Map<String,Object> params = new HashMap<>();
    params.put("_link",_link);
    consumerFunc.accept(super._send("addLink", params));
  }

  public void removeLink(String _link) {
    Map<String,Object> params = new HashMap<>();
    params.put("_link",_link);
    super._send("removeLink", params);
  }

  public void removeLink(Consumer<TransactionResult> consumerFunc, String _link) {
    Map<String,Object> params = new HashMap<>();
    params.put("_link",_link);
    consumerFunc.accept(super._send("removeLink", params));
  }

  public BMCStatus getStatus(String _link) {
    Map<String,Object> params = new HashMap<>();
    params.put("_link",_link);
    return super._call(BMCStatus.class, "getStatus", params);
  }

  public String[] getLinks() {
    return super._call(String[].class, "getLinks", null);
  }

  public void addRoute(String _dst, String _link) {
    Map<String,Object> params = new HashMap<>();
    params.put("_dst",_dst);
    params.put("_link",_link);
    super._send("addRoute", params);
  }

  public void addRoute(Consumer<TransactionResult> consumerFunc, String _dst, String _link) {
    Map<String,Object> params = new HashMap<>();
    params.put("_dst",_dst);
    params.put("_link",_link);
    consumerFunc.accept(super._send("addRoute", params));
  }

  public void removeRoute(String _dst) {
    Map<String,Object> params = new HashMap<>();
    params.put("_dst",_dst);
    super._send("removeRoute", params);
  }

  public void removeRoute(Consumer<TransactionResult> consumerFunc, String _dst) {
    Map<String,Object> params = new HashMap<>();
    params.put("_dst",_dst);
    consumerFunc.accept(super._send("removeRoute", params));
  }

  public Map getRoutes() {
    return super._call(Map.class, "getRoutes", null);
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

  public void handleRelayMessage(String _prev, String _msg) {
    Map<String,Object> params = new HashMap<>();
    params.put("_prev",_prev);
    params.put("_msg",_msg);
    super._send("handleRelayMessage", params);
  }

  public void handleRelayMessage(Consumer<TransactionResult> consumerFunc, String _prev,
      String _msg) {
    Map<String,Object> params = new HashMap<>();
    params.put("_prev",_prev);
    params.put("_msg",_msg);
    consumerFunc.accept(super._send("handleRelayMessage", params));
  }

  public String getBtpAddress() {
    return super._call(String.class, "getBtpAddress", null);
  }

  public String getNetworkAddress() {
    return super._call(String.class, "getNetworkAddress", null);
  }

  public void setFeeTable(String[] _dst, BigInteger[][] _value) {
    Map<String,Object> params = new HashMap<>();
    params.put("_dst",_dst);
    params.put("_value",_value);
    super._send("setFeeTable", params);
  }

  public void setFeeTable(Consumer<TransactionResult> consumerFunc, String[] _dst,
      BigInteger[][] _value) {
    Map<String,Object> params = new HashMap<>();
    params.put("_dst",_dst);
    params.put("_value",_value);
    consumerFunc.accept(super._send("setFeeTable", params));
  }

  public BigInteger getFee(String _to, boolean _response) {
    Map<String,Object> params = new HashMap<>();
    params.put("_to",_to);
    params.put("_response",_response);
    return super._call(BigInteger.class, "getFee", params);
  }

  public BigInteger[][] getFeeTable(String[] _dst) {
    Map<String,Object> params = new HashMap<>();
    params.put("_dst",_dst);
    return super._call(BigInteger[][].class, "getFeeTable", params);
  }

  /**
   * To payable, use claimReward(BigInteger valueForPayable, ...)
   */
  public void claimReward(String _network, String _receiver) {
    Map<String,Object> params = new HashMap<>();
    params.put("_network",_network);
    params.put("_receiver",_receiver);
    super._send("claimReward", params);
  }

  public void claimReward(Consumer<TransactionResult> consumerFunc, String _network,
      String _receiver) {
    Map<String,Object> params = new HashMap<>();
    params.put("_network",_network);
    params.put("_receiver",_receiver);
    consumerFunc.accept(super._send("claimReward", params));
  }

  public void claimReward(BigInteger valueForPayable, String _network, String _receiver) {
    Map<String,Object> params = new HashMap<>();
    params.put("_network",_network);
    params.put("_receiver",_receiver);
    super._send(valueForPayable, "claimReward", params);
  }

  public void claimReward(Consumer<TransactionResult> consumerFunc, BigInteger valueForPayable,
      String _network, String _receiver) {
    Map<String,Object> params = new HashMap<>();
    params.put("_network",_network);
    params.put("_receiver",_receiver);
    consumerFunc.accept(super._send(valueForPayable, "claimReward", params));
  }

  /**
   * @deprecated Do not use this method, this is generated only for preventing compile error.
   *  Instead, use N/A
   * @throws java.lang.RuntimeException("not supported EventLog method")
   */
  @Deprecated
  public void ClaimReward(score.Address _sender, String _network, String _receiver,
      BigInteger _amount, BigInteger _nsn) {
    throw new RuntimeException("not supported EventLog method");
  }

  public Consumer<TransactionResult> ClaimReward(Consumer<List<ClaimReward>> consumerFunc,
      Predicate<ClaimReward> filter) {
    return (txr) -> consumerFunc.accept(ClaimReward.eventLogs(txr, this.address, filter));
  }

  /**
   * @deprecated Do not use this method, this is generated only for preventing compile error.
   *  Instead, use N/A
   * @throws java.lang.RuntimeException("not supported EventLog method")
   */
  @Deprecated
  public void ClaimRewardResult(score.Address _sender, String _network, BigInteger _nsn,
      BigInteger _result) {
    throw new RuntimeException("not supported EventLog method");
  }

  public Consumer<TransactionResult> ClaimRewardResult(
      Consumer<List<ClaimRewardResult>> consumerFunc, Predicate<ClaimRewardResult> filter) {
    return (txr) -> consumerFunc.accept(ClaimRewardResult.eventLogs(txr, this.address, filter));
  }

  public BigInteger getReward(String _network, score.Address _addr) {
    Map<String,Object> params = new HashMap<>();
    params.put("_network",_network);
    params.put("_addr",_addr);
    return super._call(BigInteger.class, "getReward", params);
  }

  public void setFeeHandler(score.Address _addr) {
    Map<String,Object> params = new HashMap<>();
    params.put("_addr",_addr);
    super._send("setFeeHandler", params);
  }

  public void setFeeHandler(Consumer<TransactionResult> consumerFunc, score.Address _addr) {
    Map<String,Object> params = new HashMap<>();
    params.put("_addr",_addr);
    consumerFunc.accept(super._send("setFeeHandler", params));
  }

  public score.Address getFeeHandler() {
    return super._call(score.Address.class, "getFeeHandler", null);
  }

  public BigInteger getNetworkSn() {
    return super._call(BigInteger.class, "getNetworkSn", null);
  }

  /**
   * @deprecated Do not use this method, this is generated only for preventing compile error.
   *  Instead, use N/A
   * @throws java.lang.RuntimeException("not supported EventLog method")
   */
  @Deprecated
  public void Message(String _next, BigInteger _seq, byte[] _msg) {
    throw new RuntimeException("not supported EventLog method");
  }

  public Consumer<TransactionResult> Message(Consumer<List<Message>> consumerFunc,
      Predicate<Message> filter) {
    return (txr) -> consumerFunc.accept(Message.eventLogs(txr, this.address, filter));
  }

  /**
   * @deprecated Do not use this method, this is generated only for preventing compile error.
   *  Instead, use N/A
   * @throws java.lang.RuntimeException("not supported EventLog method")
   */
  @Deprecated
  public void BTPEvent(String _src, BigInteger _nsn, String _next, String _event) {
    throw new RuntimeException("not supported EventLog method");
  }

  public Consumer<TransactionResult> BTPEvent(Consumer<List<BTPEvent>> consumerFunc,
      Predicate<BTPEvent> filter) {
    return (txr) -> consumerFunc.accept(BTPEvent.eventLogs(txr, this.address, filter));
  }

  public static BMCScoreClient _deploy(String url, BigInteger nid, Wallet wallet,
      String scoreFilePath, Map<String, Object> params) {
    return new BMCScoreClient(DefaultScoreClient._deploy(url,nid,wallet,scoreFilePath,params));
  }

  public static BMCScoreClient _of(String prefix, Properties properties,
      Map<String, Object> params) {
    return new BMCScoreClient(DefaultScoreClient.of(prefix, properties, params));
  }

  public static class ClaimReward {
    public static final String SIGNATURE = "ClaimReward(Address,str,str,int,int)";

    public static final int INDEXED = 2;

    private final score.Address _sender;

    private final String _network;

    private final String _receiver;

    private final BigInteger _amount;

    private final BigInteger _nsn;

    public ClaimReward(TransactionResult.EventLog el) {
      List<String> indexed = el.getIndexed();
      List<String> data = el.getData();
      this._sender=IconStringConverter.toAddress(indexed.get(1));
      this._network=indexed.get(2);
      this._receiver=data.get(0);
      this._amount=IconStringConverter.toBigInteger(data.get(1));
      this._nsn=IconStringConverter.toBigInteger(data.get(2));
    }

    public score.Address get_sender() {
      return this._sender;
    }

    public String get_network() {
      return this._network;
    }

    public String get_receiver() {
      return this._receiver;
    }

    public BigInteger get_amount() {
      return this._amount;
    }

    public BigInteger get_nsn() {
      return this._nsn;
    }

    public static List<ClaimReward> eventLogs(TransactionResult txr, Address address,
        Predicate<ClaimReward> filter) {
      return DefaultScoreClient.eventLogs(txr, SIGNATURE, address, ClaimReward::new, filter);
    }
  }

  public static class ClaimRewardResult {
    public static final String SIGNATURE = "ClaimRewardResult(Address,str,int,int)";

    public static final int INDEXED = 2;

    private final score.Address _sender;

    private final String _network;

    private final BigInteger _nsn;

    private final BigInteger _result;

    public ClaimRewardResult(TransactionResult.EventLog el) {
      List<String> indexed = el.getIndexed();
      List<String> data = el.getData();
      this._sender=IconStringConverter.toAddress(indexed.get(1));
      this._network=indexed.get(2);
      this._nsn=IconStringConverter.toBigInteger(data.get(0));
      this._result=IconStringConverter.toBigInteger(data.get(1));
    }

    public score.Address get_sender() {
      return this._sender;
    }

    public String get_network() {
      return this._network;
    }

    public BigInteger get_nsn() {
      return this._nsn;
    }

    public BigInteger get_result() {
      return this._result;
    }

    public static List<ClaimRewardResult> eventLogs(TransactionResult txr, Address address,
        Predicate<ClaimRewardResult> filter) {
      return DefaultScoreClient.eventLogs(txr, SIGNATURE, address, ClaimRewardResult::new, filter);
    }
  }

  public static class Message {
    public static final String SIGNATURE = "Message(str,int,bytes)";

    public static final int INDEXED = 2;

    private final String _next;

    private final BigInteger _seq;

    private final byte[] _msg;

    public Message(TransactionResult.EventLog el) {
      List<String> indexed = el.getIndexed();
      List<String> data = el.getData();
      this._next=indexed.get(1);
      this._seq=IconStringConverter.toBigInteger(indexed.get(2));
      this._msg=IconStringConverter.toBytes(data.get(0));
    }

    public String get_next() {
      return this._next;
    }

    public BigInteger get_seq() {
      return this._seq;
    }

    public byte[] get_msg() {
      return this._msg;
    }

    public static List<Message> eventLogs(TransactionResult txr, Address address,
        Predicate<Message> filter) {
      return DefaultScoreClient.eventLogs(txr, SIGNATURE, address, Message::new, filter);
    }
  }

  public static class BTPEvent {
    public static final String SIGNATURE = "BTPEvent(str,int,str,str)";

    public static final int INDEXED = 2;

    private final String _src;

    private final BigInteger _nsn;

    private final String _next;

    private final String _event;

    public BTPEvent(TransactionResult.EventLog el) {
      List<String> indexed = el.getIndexed();
      List<String> data = el.getData();
      this._src=indexed.get(1);
      this._nsn=IconStringConverter.toBigInteger(indexed.get(2));
      this._next=data.get(0);
      this._event=data.get(1);
    }

    public String get_src() {
      return this._src;
    }

    public BigInteger get_nsn() {
      return this._nsn;
    }

    public String get_next() {
      return this._next;
    }

    public String get_event() {
      return this._event;
    }

    public static List<BTPEvent> eventLogs(TransactionResult txr, Address address,
        Predicate<BTPEvent> filter) {
      return DefaultScoreClient.eventLogs(txr, SIGNATURE, address, BTPEvent::new, filter);
    }
  }
}
