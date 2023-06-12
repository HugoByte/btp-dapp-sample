package foundation.icon.btp.lib;

import foundation.icon.jsonrpc.Address;
import foundation.icon.jsonrpc.model.TransactionResult;
import foundation.icon.score.client.DefaultScoreClient;
import foundation.icon.score.client.Wallet;
import java.lang.Boolean;
import java.lang.Object;
import java.lang.String;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.function.Consumer;

public final class OwnerManagerScoreClient extends DefaultScoreClient implements OwnerManager {
  public OwnerManagerScoreClient(String url, BigInteger nid, Wallet wallet, Address address) {
    super(url, nid, wallet, address);
  }

  public OwnerManagerScoreClient(String url, BigInteger nid, BigInteger stepLimit, Wallet wallet,
      Address address) {
    super(url, nid, stepLimit, wallet, address);
  }

  public OwnerManagerScoreClient(DefaultScoreClient client) {
    super(client);
  }

  public OwnerManagerScoreClient(DefaultScoreClient client, Wallet wallet) {
    super(client, wallet);
  }

  public static OwnerManagerScoreClient _of(Properties properties) {
    return _of("", properties);
  }

  public static OwnerManagerScoreClient _of(String prefix, Properties properties) {
    return new OwnerManagerScoreClient(DefaultScoreClient.of(prefix, properties));
  }

  public void addOwner(score.Address _addr) {
    Map<String,Object> params = new HashMap<>();
    params.put("_addr",_addr);
    super._send("addOwner", params);
  }

  public void addOwner(Consumer<TransactionResult> consumerFunc, score.Address _addr) {
    Map<String,Object> params = new HashMap<>();
    params.put("_addr",_addr);
    consumerFunc.accept(super._send("addOwner", params));
  }

  public void removeOwner(score.Address _addr) {
    Map<String,Object> params = new HashMap<>();
    params.put("_addr",_addr);
    super._send("removeOwner", params);
  }

  public void removeOwner(Consumer<TransactionResult> consumerFunc, score.Address _addr) {
    Map<String,Object> params = new HashMap<>();
    params.put("_addr",_addr);
    consumerFunc.accept(super._send("removeOwner", params));
  }

  public score.Address[] getOwners() {
    return super._call(score.Address[].class, "getOwners", null);
  }

  public boolean isOwner(score.Address _addr) {
    Map<String,Object> params = new HashMap<>();
    params.put("_addr",_addr);
    return super._call(Boolean.class, "isOwner", params);
  }

  public static OwnerManagerScoreClient _deploy(String url, BigInteger nid, Wallet wallet,
      String scoreFilePath, Map<String, Object> params) {
    return new OwnerManagerScoreClient(DefaultScoreClient._deploy(url,nid,wallet,scoreFilePath,params));
  }

  public static OwnerManagerScoreClient _of(String prefix, Properties properties,
      Map<String, Object> params) {
    return new OwnerManagerScoreClient(DefaultScoreClient.of(prefix, properties, params));
  }
}
