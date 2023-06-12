package foundation.icon.btp.mock;

import foundation.icon.btp.lib.BMV;
import foundation.icon.btp.lib.BMVStatus;
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

public final class MockBMVScoreClient extends DefaultScoreClient implements BMV, MockBMV {
  public MockBMVScoreClient(String url, BigInteger nid, Wallet wallet, Address address) {
    super(url, nid, wallet, address);
  }

  public MockBMVScoreClient(String url, BigInteger nid, BigInteger stepLimit, Wallet wallet,
      Address address) {
    super(url, nid, stepLimit, wallet, address);
  }

  public MockBMVScoreClient(DefaultScoreClient client) {
    super(client);
  }

  public MockBMVScoreClient(DefaultScoreClient client, Wallet wallet) {
    super(client, wallet);
  }

  public static MockBMVScoreClient _of(Properties properties) {
    return _of("", properties);
  }

  public static MockBMVScoreClient _of(String prefix, Properties properties) {
    return new MockBMVScoreClient(DefaultScoreClient.of(prefix, properties));
  }

  public BMVStatus getStatus() {
    return super._call(BMVStatus.class, "getStatus", null);
  }

  /**
   * @deprecated Do not use this method, this is generated only for preventing compile error.
   *  Instead, use handleRelayMessage(Consumer<TransactionResult> consumerFunc, ...)
   * @throws java.lang.RuntimeException("not supported response of writable method in ScoreClient")
   */
  @Deprecated
  public byte[][] handleRelayMessage(String arg0, String arg1, BigInteger arg2, byte[] arg3) {
    throw new RuntimeException("not supported response of writable method in ScoreClient");
  }

  public void handleRelayMessage(Consumer<TransactionResult> consumerFunc, String arg0, String arg1,
      BigInteger arg2, byte[] arg3) {
    Map<String,Object> params = new HashMap<>();
    params.put("arg0",arg0);
    params.put("arg1",arg1);
    params.put("arg2",arg2);
    params.put("arg3",arg3);
    consumerFunc.accept(super._send("handleRelayMessage", params));
  }

  public void setHeight(long _height) {
    Map<String,Object> params = new HashMap<>();
    params.put("_height",_height);
    super._send("setHeight", params);
  }

  public void setHeight(Consumer<TransactionResult> consumerFunc, long _height) {
    Map<String,Object> params = new HashMap<>();
    params.put("_height",_height);
    consumerFunc.accept(super._send("setHeight", params));
  }

  public void setOffset(long _offset) {
    Map<String,Object> params = new HashMap<>();
    params.put("_offset",_offset);
    super._send("setOffset", params);
  }

  public void setOffset(Consumer<TransactionResult> consumerFunc, long _offset) {
    Map<String,Object> params = new HashMap<>();
    params.put("_offset",_offset);
    consumerFunc.accept(super._send("setOffset", params));
  }

  public void setLast_height(long _last_height) {
    Map<String,Object> params = new HashMap<>();
    params.put("_last_height",_last_height);
    super._send("setLast_height", params);
  }

  public void setLast_height(Consumer<TransactionResult> consumerFunc, long _last_height) {
    Map<String,Object> params = new HashMap<>();
    params.put("_last_height",_last_height);
    consumerFunc.accept(super._send("setLast_height", params));
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

  public static MockBMVScoreClient _deploy(String url, BigInteger nid, Wallet wallet,
      String scoreFilePath, Map<String, Object> params) {
    return new MockBMVScoreClient(DefaultScoreClient._deploy(url,nid,wallet,scoreFilePath,params));
  }

  public static MockBMVScoreClient _of(String prefix, Properties properties,
      Map<String, Object> params) {
    return new MockBMVScoreClient(DefaultScoreClient.of(prefix, properties, params));
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
}
