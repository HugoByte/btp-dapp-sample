package foundation.icon.btp.lib;

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

public final class BMVScoreClient extends DefaultScoreClient implements BMV {
  public BMVScoreClient(String url, BigInteger nid, Wallet wallet, Address address) {
    super(url, nid, wallet, address);
  }

  public BMVScoreClient(String url, BigInteger nid, BigInteger stepLimit, Wallet wallet,
      Address address) {
    super(url, nid, stepLimit, wallet, address);
  }

  public BMVScoreClient(DefaultScoreClient client) {
    super(client);
  }

  public BMVScoreClient(DefaultScoreClient client, Wallet wallet) {
    super(client, wallet);
  }

  public static BMVScoreClient _of(Properties properties) {
    return _of("", properties);
  }

  public static BMVScoreClient _of(String prefix, Properties properties) {
    return new BMVScoreClient(DefaultScoreClient.of(prefix, properties));
  }

  /**
   * @deprecated Do not use this method, this is generated only for preventing compile error.
   *  Instead, use handleRelayMessage(Consumer<TransactionResult> consumerFunc, ...)
   * @throws java.lang.RuntimeException("not supported response of writable method in ScoreClient")
   */
  @Deprecated
  public byte[][] handleRelayMessage(String _bmc, String _prev, BigInteger _seq, byte[] _msg) {
    throw new RuntimeException("not supported response of writable method in ScoreClient");
  }

  public void handleRelayMessage(Consumer<TransactionResult> consumerFunc, String _bmc,
      String _prev, BigInteger _seq, byte[] _msg) {
    Map<String,Object> params = new HashMap<>();
    params.put("_bmc",_bmc);
    params.put("_prev",_prev);
    params.put("_seq",_seq);
    params.put("_msg",_msg);
    consumerFunc.accept(super._send("handleRelayMessage", params));
  }

  public BMVStatus getStatus() {
    return super._call(BMVStatus.class, "getStatus", null);
  }

  public static BMVScoreClient _deploy(String url, BigInteger nid, Wallet wallet,
      String scoreFilePath, Map<String, Object> params) {
    return new BMVScoreClient(DefaultScoreClient._deploy(url,nid,wallet,scoreFilePath,params));
  }

  public static BMVScoreClient _of(String prefix, Properties properties,
      Map<String, Object> params) {
    return new BMVScoreClient(DefaultScoreClient.of(prefix, properties, params));
  }
}
