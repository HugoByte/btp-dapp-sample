package foundation.icon.btp.mock;

import foundation.icon.jsonrpc.Address;
import foundation.icon.jsonrpc.model.TransactionResult;
import foundation.icon.score.client.DefaultScoreClient;
import foundation.icon.score.client.Wallet;
import java.lang.Deprecated;
import java.lang.Object;
import java.lang.RuntimeException;
import java.lang.String;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.function.Consumer;

public final class MockGovScoreClient extends DefaultScoreClient implements MockGov {
  public MockGovScoreClient(String url, BigInteger nid, Wallet wallet, Address address) {
    super(url, nid, wallet, address);
  }

  public MockGovScoreClient(String url, BigInteger nid, BigInteger stepLimit, Wallet wallet,
      Address address) {
    super(url, nid, stepLimit, wallet, address);
  }

  public MockGovScoreClient(DefaultScoreClient client) {
    super(client);
  }

  public MockGovScoreClient(DefaultScoreClient client, Wallet wallet) {
    super(client, wallet);
  }

  public static MockGovScoreClient _of(Properties properties) {
    return _of("", properties);
  }

  public static MockGovScoreClient _of(String prefix, Properties properties) {
    return new MockGovScoreClient(DefaultScoreClient.of(prefix, properties));
  }

  public void setRevision(int code) {
    Map<String,Object> params = new HashMap<>();
    params.put("code",code);
    super._send("setRevision", params);
  }

  public void setRevision(Consumer<TransactionResult> consumerFunc, int code) {
    Map<String,Object> params = new HashMap<>();
    params.put("code",code);
    consumerFunc.accept(super._send("setRevision", params));
  }

  public void setMaxStepLimit(String contextType, BigInteger limit) {
    Map<String,Object> params = new HashMap<>();
    params.put("contextType",contextType);
    params.put("limit",limit);
    super._send("setMaxStepLimit", params);
  }

  public void setMaxStepLimit(Consumer<TransactionResult> consumerFunc, String contextType,
      BigInteger limit) {
    Map<String,Object> params = new HashMap<>();
    params.put("contextType",contextType);
    params.put("limit",limit);
    consumerFunc.accept(super._send("setMaxStepLimit", params));
  }

  /**
   * @deprecated Do not use this method, this is generated only for preventing compile error.
   *  Instead, use openBTPNetwork(Consumer<TransactionResult> consumerFunc, ...)
   * @throws java.lang.RuntimeException("not supported response of writable method in ScoreClient")
   */
  @Deprecated
  public long openBTPNetwork(String networkTypeName, String name, score.Address owner) {
    throw new RuntimeException("not supported response of writable method in ScoreClient");
  }

  public void openBTPNetwork(Consumer<TransactionResult> consumerFunc, String networkTypeName,
      String name, score.Address owner) {
    Map<String,Object> params = new HashMap<>();
    params.put("networkTypeName",networkTypeName);
    params.put("name",name);
    params.put("owner",owner);
    consumerFunc.accept(super._send("openBTPNetwork", params));
  }

  public void closeBTPNetwork(long id) {
    Map<String,Object> params = new HashMap<>();
    params.put("id",id);
    super._send("closeBTPNetwork", params);
  }

  public void closeBTPNetwork(Consumer<TransactionResult> consumerFunc, long id) {
    Map<String,Object> params = new HashMap<>();
    params.put("id",id);
    consumerFunc.accept(super._send("closeBTPNetwork", params));
  }

  public static MockGovScoreClient _deploy(String url, BigInteger nid, Wallet wallet,
      String scoreFilePath, Map<String, Object> params) {
    return new MockGovScoreClient(DefaultScoreClient._deploy(url,nid,wallet,scoreFilePath,params));
  }

  public static MockGovScoreClient _of(String prefix, Properties properties,
      Map<String, Object> params) {
    return new MockGovScoreClient(DefaultScoreClient.of(prefix, properties, params));
  }
}
