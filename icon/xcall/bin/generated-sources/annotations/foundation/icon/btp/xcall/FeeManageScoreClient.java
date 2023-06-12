package foundation.icon.btp.xcall;

import foundation.icon.jsonrpc.Address;
import foundation.icon.jsonrpc.model.TransactionResult;
import foundation.icon.score.client.DefaultScoreClient;
import foundation.icon.score.client.Wallet;
import java.lang.Object;
import java.lang.String;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.function.Consumer;
import score.annotation.Optional;

public final class FeeManageScoreClient extends DefaultScoreClient implements FeeManage {
  public FeeManageScoreClient(String url, BigInteger nid, Wallet wallet, Address address) {
    super(url, nid, wallet, address);
  }

  public FeeManageScoreClient(String url, BigInteger nid, BigInteger stepLimit, Wallet wallet,
      Address address) {
    super(url, nid, stepLimit, wallet, address);
  }

  public FeeManageScoreClient(DefaultScoreClient client) {
    super(client);
  }

  public FeeManageScoreClient(DefaultScoreClient client, Wallet wallet) {
    super(client, wallet);
  }

  public static FeeManageScoreClient _of(Properties properties) {
    return _of("", properties);
  }

  public static FeeManageScoreClient _of(String prefix, Properties properties) {
    return new FeeManageScoreClient(DefaultScoreClient.of(prefix, properties));
  }

  public void setProtocolFeeHandler(@Optional score.Address _addr) {
    Map<String,Object> params = new HashMap<>();
    params.put("_addr",_addr);
    super._send("setProtocolFeeHandler", params);
  }

  public void setProtocolFeeHandler(Consumer<TransactionResult> consumerFunc,
      @Optional score.Address _addr) {
    Map<String,Object> params = new HashMap<>();
    params.put("_addr",_addr);
    consumerFunc.accept(super._send("setProtocolFeeHandler", params));
  }

  public score.Address getProtocolFeeHandler() {
    return super._call(score.Address.class, "getProtocolFeeHandler", null);
  }

  public void setProtocolFee(BigInteger _value) {
    Map<String,Object> params = new HashMap<>();
    params.put("_value",_value);
    super._send("setProtocolFee", params);
  }

  public void setProtocolFee(Consumer<TransactionResult> consumerFunc, BigInteger _value) {
    Map<String,Object> params = new HashMap<>();
    params.put("_value",_value);
    consumerFunc.accept(super._send("setProtocolFee", params));
  }

  public BigInteger getProtocolFee() {
    return super._call(BigInteger.class, "getProtocolFee", null);
  }

  public BigInteger getFee(String _net, boolean _rollback) {
    Map<String,Object> params = new HashMap<>();
    params.put("_net",_net);
    params.put("_rollback",_rollback);
    return super._call(BigInteger.class, "getFee", params);
  }

  public static FeeManageScoreClient _deploy(String url, BigInteger nid, Wallet wallet,
      String scoreFilePath, Map<String, Object> params) {
    return new FeeManageScoreClient(DefaultScoreClient._deploy(url,nid,wallet,scoreFilePath,params));
  }

  public static FeeManageScoreClient _of(String prefix, Properties properties,
      Map<String, Object> params) {
    return new FeeManageScoreClient(DefaultScoreClient.of(prefix, properties, params));
  }
}
