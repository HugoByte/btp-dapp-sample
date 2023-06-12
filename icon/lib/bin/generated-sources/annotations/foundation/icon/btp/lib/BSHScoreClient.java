package foundation.icon.btp.lib;

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

public final class BSHScoreClient extends DefaultScoreClient implements BSH {
  public BSHScoreClient(String url, BigInteger nid, Wallet wallet, Address address) {
    super(url, nid, wallet, address);
  }

  public BSHScoreClient(String url, BigInteger nid, BigInteger stepLimit, Wallet wallet,
      Address address) {
    super(url, nid, stepLimit, wallet, address);
  }

  public BSHScoreClient(DefaultScoreClient client) {
    super(client);
  }

  public BSHScoreClient(DefaultScoreClient client, Wallet wallet) {
    super(client, wallet);
  }

  public static BSHScoreClient _of(Properties properties) {
    return _of("", properties);
  }

  public static BSHScoreClient _of(String prefix, Properties properties) {
    return new BSHScoreClient(DefaultScoreClient.of(prefix, properties));
  }

  public void handleBTPMessage(String _from, String _svc, BigInteger _sn, byte[] _msg) {
    Map<String,Object> params = new HashMap<>();
    params.put("_from",_from);
    params.put("_svc",_svc);
    params.put("_sn",_sn);
    params.put("_msg",_msg);
    super._send("handleBTPMessage", params);
  }

  public void handleBTPMessage(Consumer<TransactionResult> consumerFunc, String _from, String _svc,
      BigInteger _sn, byte[] _msg) {
    Map<String,Object> params = new HashMap<>();
    params.put("_from",_from);
    params.put("_svc",_svc);
    params.put("_sn",_sn);
    params.put("_msg",_msg);
    consumerFunc.accept(super._send("handleBTPMessage", params));
  }

  public void handleBTPError(String _src, String _svc, BigInteger _sn, long _code, String _msg) {
    Map<String,Object> params = new HashMap<>();
    params.put("_src",_src);
    params.put("_svc",_svc);
    params.put("_sn",_sn);
    params.put("_code",_code);
    params.put("_msg",_msg);
    super._send("handleBTPError", params);
  }

  public void handleBTPError(Consumer<TransactionResult> consumerFunc, String _src, String _svc,
      BigInteger _sn, long _code, String _msg) {
    Map<String,Object> params = new HashMap<>();
    params.put("_src",_src);
    params.put("_svc",_svc);
    params.put("_sn",_sn);
    params.put("_code",_code);
    params.put("_msg",_msg);
    consumerFunc.accept(super._send("handleBTPError", params));
  }

  public static BSHScoreClient _deploy(String url, BigInteger nid, Wallet wallet,
      String scoreFilePath, Map<String, Object> params) {
    return new BSHScoreClient(DefaultScoreClient._deploy(url,nid,wallet,scoreFilePath,params));
  }

  public static BSHScoreClient _of(String prefix, Properties properties,
      Map<String, Object> params) {
    return new BSHScoreClient(DefaultScoreClient.of(prefix, properties, params));
  }
}
