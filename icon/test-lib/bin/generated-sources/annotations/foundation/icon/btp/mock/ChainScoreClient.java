package foundation.icon.btp.mock;

import foundation.icon.jsonrpc.Address;
import foundation.icon.jsonrpc.IconStringConverter;
import foundation.icon.jsonrpc.model.TransactionResult;
import foundation.icon.score.client.DefaultScoreClient;
import foundation.icon.score.client.Wallet;
import java.lang.Deprecated;
import java.lang.Integer;
import java.lang.Long;
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

public final class ChainScoreClient extends DefaultScoreClient implements ChainScore {
  public ChainScoreClient(String url, BigInteger nid, Wallet wallet, Address address) {
    super(url, nid, wallet, address);
  }

  public ChainScoreClient(String url, BigInteger nid, BigInteger stepLimit, Wallet wallet,
      Address address) {
    super(url, nid, stepLimit, wallet, address);
  }

  public ChainScoreClient(DefaultScoreClient client) {
    super(client);
  }

  public ChainScoreClient(DefaultScoreClient client, Wallet wallet) {
    super(client, wallet);
  }

  public static ChainScoreClient _of(Properties properties) {
    return _of("", properties);
  }

  public static ChainScoreClient _of(String prefix, Properties properties) {
    return new ChainScoreClient(DefaultScoreClient.of(prefix, properties));
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

  public BigInteger getStepPrice() {
    return super._call(BigInteger.class, "getStepPrice", null);
  }

  public BigInteger getStepCost(String type) {
    Map<String,Object> params = new HashMap<>();
    params.put("type",type);
    return super._call(BigInteger.class, "getStepCost", params);
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

  public BigInteger getMaxStepLimit(String contextType) {
    Map<String,Object> params = new HashMap<>();
    params.put("contextType",contextType);
    return super._call(BigInteger.class, "getMaxStepLimit", params);
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

  /**
   * @deprecated Do not use this method, this is generated only for preventing compile error.
   *  Instead, use N/A
   * @throws java.lang.RuntimeException("not supported EventLog method")
   */
  @Deprecated
  public void BTPNetworkTypeActivated(String networkTypeName, long networkTypeID) {
    throw new RuntimeException("not supported EventLog method");
  }

  public Consumer<TransactionResult> BTPNetworkTypeActivated(
      Consumer<List<BTPNetworkTypeActivated>> consumerFunc,
      Predicate<BTPNetworkTypeActivated> filter) {
    return (txr) -> consumerFunc.accept(BTPNetworkTypeActivated.eventLogs(txr, this.address, filter));
  }

  /**
   * @deprecated Do not use this method, this is generated only for preventing compile error.
   *  Instead, use N/A
   * @throws java.lang.RuntimeException("not supported EventLog method")
   */
  @Deprecated
  public void BTPNetworkOpened(long networkTypeID, long networkID) {
    throw new RuntimeException("not supported EventLog method");
  }

  public Consumer<TransactionResult> BTPNetworkOpened(Consumer<List<BTPNetworkOpened>> consumerFunc,
      Predicate<BTPNetworkOpened> filter) {
    return (txr) -> consumerFunc.accept(BTPNetworkOpened.eventLogs(txr, this.address, filter));
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

  /**
   * @deprecated Do not use this method, this is generated only for preventing compile error.
   *  Instead, use N/A
   * @throws java.lang.RuntimeException("not supported EventLog method")
   */
  @Deprecated
  public void BTPNetworkClosed(long networkTypeID, long networkID) {
    throw new RuntimeException("not supported EventLog method");
  }

  public Consumer<TransactionResult> BTPNetworkClosed(Consumer<List<BTPNetworkClosed>> consumerFunc,
      Predicate<BTPNetworkClosed> filter) {
    return (txr) -> consumerFunc.accept(BTPNetworkClosed.eventLogs(txr, this.address, filter));
  }

  public int getRevision() {
    return super._call(Integer.class, "getRevision", null);
  }

  public void setBTPPublicKey(String name, byte[] pubKey) {
    Map<String,Object> params = new HashMap<>();
    params.put("name",name);
    params.put("pubKey",pubKey);
    super._send("setBTPPublicKey", params);
  }

  public void setBTPPublicKey(Consumer<TransactionResult> consumerFunc, String name,
      byte[] pubKey) {
    Map<String,Object> params = new HashMap<>();
    params.put("name",name);
    params.put("pubKey",pubKey);
    consumerFunc.accept(super._send("setBTPPublicKey", params));
  }

  public byte[] getBTPPublicKey(score.Address address, String name) {
    Map<String,Object> params = new HashMap<>();
    params.put("address",address);
    params.put("name",name);
    return super._call(byte[].class, "getBTPPublicKey", params);
  }

  public long getBTPNetworkTypeID(String name) {
    Map<String,Object> params = new HashMap<>();
    params.put("name",name);
    return super._call(Long.class, "getBTPNetworkTypeID", params);
  }

  public void sendBTPMessage(long networkId, byte[] message) {
    Map<String,Object> params = new HashMap<>();
    params.put("networkId",networkId);
    params.put("message",message);
    super._send("sendBTPMessage", params);
  }

  public void sendBTPMessage(Consumer<TransactionResult> consumerFunc, long networkId,
      byte[] message) {
    Map<String,Object> params = new HashMap<>();
    params.put("networkId",networkId);
    params.put("message",message);
    consumerFunc.accept(super._send("sendBTPMessage", params));
  }

  public static ChainScoreClient _deploy(String url, BigInteger nid, Wallet wallet,
      String scoreFilePath, Map<String, Object> params) {
    return new ChainScoreClient(DefaultScoreClient._deploy(url,nid,wallet,scoreFilePath,params));
  }

  public static ChainScoreClient _of(String prefix, Properties properties,
      Map<String, Object> params) {
    return new ChainScoreClient(DefaultScoreClient.of(prefix, properties, params));
  }

  public static class BTPNetworkTypeActivated {
    public static final String SIGNATURE = "BTPNetworkTypeActivated(str,int)";

    public static final int INDEXED = 2;

    private final String networkTypeName;

    private final long networkTypeID;

    public BTPNetworkTypeActivated(TransactionResult.EventLog el) {
      List<String> indexed = el.getIndexed();
      List<String> data = el.getData();
      this.networkTypeName=indexed.get(1);
      this.networkTypeID=IconStringConverter.toBigInteger(indexed.get(2)).longValue();
    }

    public String getNetworkTypeName() {
      return this.networkTypeName;
    }

    public long getNetworkTypeID() {
      return this.networkTypeID;
    }

    public static List<BTPNetworkTypeActivated> eventLogs(TransactionResult txr, Address address,
        Predicate<BTPNetworkTypeActivated> filter) {
      return DefaultScoreClient.eventLogs(txr, SIGNATURE, address, BTPNetworkTypeActivated::new, filter);
    }
  }

  public static class BTPNetworkOpened {
    public static final String SIGNATURE = "BTPNetworkOpened(int,int)";

    public static final int INDEXED = 2;

    private final long networkTypeID;

    private final long networkID;

    public BTPNetworkOpened(TransactionResult.EventLog el) {
      List<String> indexed = el.getIndexed();
      List<String> data = el.getData();
      this.networkTypeID=IconStringConverter.toBigInteger(indexed.get(1)).longValue();
      this.networkID=IconStringConverter.toBigInteger(indexed.get(2)).longValue();
    }

    public long getNetworkTypeID() {
      return this.networkTypeID;
    }

    public long getNetworkID() {
      return this.networkID;
    }

    public static List<BTPNetworkOpened> eventLogs(TransactionResult txr, Address address,
        Predicate<BTPNetworkOpened> filter) {
      return DefaultScoreClient.eventLogs(txr, SIGNATURE, address, BTPNetworkOpened::new, filter);
    }
  }

  public static class BTPNetworkClosed {
    public static final String SIGNATURE = "BTPNetworkClosed(int,int)";

    public static final int INDEXED = 2;

    private final long networkTypeID;

    private final long networkID;

    public BTPNetworkClosed(TransactionResult.EventLog el) {
      List<String> indexed = el.getIndexed();
      List<String> data = el.getData();
      this.networkTypeID=IconStringConverter.toBigInteger(indexed.get(1)).longValue();
      this.networkID=IconStringConverter.toBigInteger(indexed.get(2)).longValue();
    }

    public long getNetworkTypeID() {
      return this.networkTypeID;
    }

    public long getNetworkID() {
      return this.networkID;
    }

    public static List<BTPNetworkClosed> eventLogs(TransactionResult txr, Address address,
        Predicate<BTPNetworkClosed> filter) {
      return DefaultScoreClient.eventLogs(txr, SIGNATURE, address, BTPNetworkClosed::new, filter);
    }
  }
}
