package foundation.icon.btp.xcall;

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
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.function.Consumer;
import java.util.function.Predicate;

public final class CallServiceEventScoreClient extends DefaultScoreClient implements CallServiceEvent {
  public CallServiceEventScoreClient(String url, BigInteger nid, Wallet wallet, Address address) {
    super(url, nid, wallet, address);
  }

  public CallServiceEventScoreClient(String url, BigInteger nid, BigInteger stepLimit,
      Wallet wallet, Address address) {
    super(url, nid, stepLimit, wallet, address);
  }

  public CallServiceEventScoreClient(DefaultScoreClient client) {
    super(client);
  }

  public CallServiceEventScoreClient(DefaultScoreClient client, Wallet wallet) {
    super(client, wallet);
  }

  public static CallServiceEventScoreClient _of(Properties properties) {
    return _of("", properties);
  }

  public static CallServiceEventScoreClient _of(String prefix, Properties properties) {
    return new CallServiceEventScoreClient(DefaultScoreClient.of(prefix, properties));
  }

  /**
   * @deprecated Do not use this method, this is generated only for preventing compile error.
   *  Instead, use N/A
   * @throws java.lang.RuntimeException("not supported EventLog method")
   */
  @Deprecated
  public void CallMessageSent(score.Address _from, String _to, BigInteger _sn, BigInteger _nsn) {
    throw new RuntimeException("not supported EventLog method");
  }

  public Consumer<TransactionResult> CallMessageSent(Consumer<List<CallMessageSent>> consumerFunc,
      Predicate<CallMessageSent> filter) {
    return (txr) -> consumerFunc.accept(CallMessageSent.eventLogs(txr, this.address, filter));
  }

  /**
   * @deprecated Do not use this method, this is generated only for preventing compile error.
   *  Instead, use N/A
   * @throws java.lang.RuntimeException("not supported EventLog method")
   */
  @Deprecated
  public void ResponseMessage(BigInteger _sn, int _code, String _msg) {
    throw new RuntimeException("not supported EventLog method");
  }

  public Consumer<TransactionResult> ResponseMessage(Consumer<List<ResponseMessage>> consumerFunc,
      Predicate<ResponseMessage> filter) {
    return (txr) -> consumerFunc.accept(ResponseMessage.eventLogs(txr, this.address, filter));
  }

  /**
   * @deprecated Do not use this method, this is generated only for preventing compile error.
   *  Instead, use N/A
   * @throws java.lang.RuntimeException("not supported EventLog method")
   */
  @Deprecated
  public void RollbackMessage(BigInteger _sn) {
    throw new RuntimeException("not supported EventLog method");
  }

  public Consumer<TransactionResult> RollbackMessage(Consumer<List<RollbackMessage>> consumerFunc,
      Predicate<RollbackMessage> filter) {
    return (txr) -> consumerFunc.accept(RollbackMessage.eventLogs(txr, this.address, filter));
  }

  /**
   * @deprecated Do not use this method, this is generated only for preventing compile error.
   *  Instead, use N/A
   * @throws java.lang.RuntimeException("not supported EventLog method")
   */
  @Deprecated
  public void RollbackExecuted(BigInteger _sn, int _code, String _msg) {
    throw new RuntimeException("not supported EventLog method");
  }

  public Consumer<TransactionResult> RollbackExecuted(Consumer<List<RollbackExecuted>> consumerFunc,
      Predicate<RollbackExecuted> filter) {
    return (txr) -> consumerFunc.accept(RollbackExecuted.eventLogs(txr, this.address, filter));
  }

  /**
   * @deprecated Do not use this method, this is generated only for preventing compile error.
   *  Instead, use N/A
   * @throws java.lang.RuntimeException("not supported EventLog method")
   */
  @Deprecated
  public void CallMessage(String _from, String _to, BigInteger _sn, BigInteger _reqId) {
    throw new RuntimeException("not supported EventLog method");
  }

  public Consumer<TransactionResult> CallMessage(Consumer<List<CallMessage>> consumerFunc,
      Predicate<CallMessage> filter) {
    return (txr) -> consumerFunc.accept(CallMessage.eventLogs(txr, this.address, filter));
  }

  /**
   * @deprecated Do not use this method, this is generated only for preventing compile error.
   *  Instead, use N/A
   * @throws java.lang.RuntimeException("not supported EventLog method")
   */
  @Deprecated
  public void CallExecuted(BigInteger _reqId, int _code, String _msg) {
    throw new RuntimeException("not supported EventLog method");
  }

  public Consumer<TransactionResult> CallExecuted(Consumer<List<CallExecuted>> consumerFunc,
      Predicate<CallExecuted> filter) {
    return (txr) -> consumerFunc.accept(CallExecuted.eventLogs(txr, this.address, filter));
  }

  public static CallServiceEventScoreClient _deploy(String url, BigInteger nid, Wallet wallet,
      String scoreFilePath, Map<String, Object> params) {
    return new CallServiceEventScoreClient(DefaultScoreClient._deploy(url,nid,wallet,scoreFilePath,params));
  }

  public static CallServiceEventScoreClient _of(String prefix, Properties properties,
      Map<String, Object> params) {
    return new CallServiceEventScoreClient(DefaultScoreClient.of(prefix, properties, params));
  }

  public static class CallMessageSent {
    public static final String SIGNATURE = "CallMessageSent(Address,str,int,int)";

    public static final int INDEXED = 3;

    private final score.Address _from;

    private final String _to;

    private final BigInteger _sn;

    private final BigInteger _nsn;

    public CallMessageSent(TransactionResult.EventLog el) {
      List<String> indexed = el.getIndexed();
      List<String> data = el.getData();
      this._from=IconStringConverter.toAddress(indexed.get(1));
      this._to=indexed.get(2);
      this._sn=IconStringConverter.toBigInteger(indexed.get(3));
      this._nsn=IconStringConverter.toBigInteger(data.get(0));
    }

    public score.Address get_from() {
      return this._from;
    }

    public String get_to() {
      return this._to;
    }

    public BigInteger get_sn() {
      return this._sn;
    }

    public BigInteger get_nsn() {
      return this._nsn;
    }

    public static List<CallMessageSent> eventLogs(TransactionResult txr, Address address,
        Predicate<CallMessageSent> filter) {
      return DefaultScoreClient.eventLogs(txr, SIGNATURE, address, CallMessageSent::new, filter);
    }
  }

  public static class ResponseMessage {
    public static final String SIGNATURE = "ResponseMessage(int,int,str)";

    public static final int INDEXED = 1;

    private final BigInteger _sn;

    private final int _code;

    private final String _msg;

    public ResponseMessage(TransactionResult.EventLog el) {
      List<String> indexed = el.getIndexed();
      List<String> data = el.getData();
      this._sn=IconStringConverter.toBigInteger(indexed.get(1));
      this._code=IconStringConverter.toBigInteger(data.get(0)).intValue();
      this._msg=data.get(1);
    }

    public BigInteger get_sn() {
      return this._sn;
    }

    public int get_code() {
      return this._code;
    }

    public String get_msg() {
      return this._msg;
    }

    public static List<ResponseMessage> eventLogs(TransactionResult txr, Address address,
        Predicate<ResponseMessage> filter) {
      return DefaultScoreClient.eventLogs(txr, SIGNATURE, address, ResponseMessage::new, filter);
    }
  }

  public static class RollbackMessage {
    public static final String SIGNATURE = "RollbackMessage(int)";

    public static final int INDEXED = 1;

    private final BigInteger _sn;

    public RollbackMessage(TransactionResult.EventLog el) {
      List<String> indexed = el.getIndexed();
      List<String> data = el.getData();
      this._sn=IconStringConverter.toBigInteger(indexed.get(1));
    }

    public BigInteger get_sn() {
      return this._sn;
    }

    public static List<RollbackMessage> eventLogs(TransactionResult txr, Address address,
        Predicate<RollbackMessage> filter) {
      return DefaultScoreClient.eventLogs(txr, SIGNATURE, address, RollbackMessage::new, filter);
    }
  }

  public static class RollbackExecuted {
    public static final String SIGNATURE = "RollbackExecuted(int,int,str)";

    public static final int INDEXED = 1;

    private final BigInteger _sn;

    private final int _code;

    private final String _msg;

    public RollbackExecuted(TransactionResult.EventLog el) {
      List<String> indexed = el.getIndexed();
      List<String> data = el.getData();
      this._sn=IconStringConverter.toBigInteger(indexed.get(1));
      this._code=IconStringConverter.toBigInteger(data.get(0)).intValue();
      this._msg=data.get(1);
    }

    public BigInteger get_sn() {
      return this._sn;
    }

    public int get_code() {
      return this._code;
    }

    public String get_msg() {
      return this._msg;
    }

    public static List<RollbackExecuted> eventLogs(TransactionResult txr, Address address,
        Predicate<RollbackExecuted> filter) {
      return DefaultScoreClient.eventLogs(txr, SIGNATURE, address, RollbackExecuted::new, filter);
    }
  }

  public static class CallMessage {
    public static final String SIGNATURE = "CallMessage(str,str,int,int)";

    public static final int INDEXED = 3;

    private final String _from;

    private final String _to;

    private final BigInteger _sn;

    private final BigInteger _reqId;

    public CallMessage(TransactionResult.EventLog el) {
      List<String> indexed = el.getIndexed();
      List<String> data = el.getData();
      this._from=indexed.get(1);
      this._to=indexed.get(2);
      this._sn=IconStringConverter.toBigInteger(indexed.get(3));
      this._reqId=IconStringConverter.toBigInteger(data.get(0));
    }

    public String get_from() {
      return this._from;
    }

    public String get_to() {
      return this._to;
    }

    public BigInteger get_sn() {
      return this._sn;
    }

    public BigInteger get_reqId() {
      return this._reqId;
    }

    public static List<CallMessage> eventLogs(TransactionResult txr, Address address,
        Predicate<CallMessage> filter) {
      return DefaultScoreClient.eventLogs(txr, SIGNATURE, address, CallMessage::new, filter);
    }
  }

  public static class CallExecuted {
    public static final String SIGNATURE = "CallExecuted(int,int,str)";

    public static final int INDEXED = 1;

    private final BigInteger _reqId;

    private final int _code;

    private final String _msg;

    public CallExecuted(TransactionResult.EventLog el) {
      List<String> indexed = el.getIndexed();
      List<String> data = el.getData();
      this._reqId=IconStringConverter.toBigInteger(indexed.get(1));
      this._code=IconStringConverter.toBigInteger(data.get(0)).intValue();
      this._msg=data.get(1);
    }

    public BigInteger get_reqId() {
      return this._reqId;
    }

    public int get_code() {
      return this._code;
    }

    public String get_msg() {
      return this._msg;
    }

    public static List<CallExecuted> eventLogs(TransactionResult txr, Address address,
        Predicate<CallExecuted> filter) {
      return DefaultScoreClient.eventLogs(txr, SIGNATURE, address, CallExecuted::new, filter);
    }
  }
}
