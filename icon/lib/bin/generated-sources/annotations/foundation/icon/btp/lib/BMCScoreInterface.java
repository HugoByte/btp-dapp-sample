package foundation.icon.btp.lib;

import java.lang.Deprecated;
import java.lang.RuntimeException;
import java.lang.String;
import java.math.BigInteger;
import java.util.Map;
import score.Address;
import score.Context;

public final class BMCScoreInterface implements BMC {
  protected final Address address;

  public BMCScoreInterface(Address address) {
    this.address = address;
  }

  public Address _address() {
    return this.address;
  }

  public void addVerifier(String _net, Address _addr) {
    Context.call(this.address, "addVerifier", _net, _addr);
  }

  public void removeVerifier(String _net) {
    Context.call(this.address, "removeVerifier", _net);
  }

  public Map getVerifiers() {
    return Context.call(Map.class, this.address, "getVerifiers");
  }

  public void addService(String _svc, Address _addr) {
    Context.call(this.address, "addService", _svc, _addr);
  }

  public void removeService(String _svc) {
    Context.call(this.address, "removeService", _svc);
  }

  public Map getServices() {
    return Context.call(Map.class, this.address, "getServices");
  }

  public void addLink(String _link) {
    Context.call(this.address, "addLink", _link);
  }

  public void removeLink(String _link) {
    Context.call(this.address, "removeLink", _link);
  }

  public BMCStatus getStatus(String _link) {
    return Context.call(BMCStatus.class, this.address, "getStatus", _link);
  }

  public String[] getLinks() {
    return Context.call(String[].class, this.address, "getLinks");
  }

  public void addRoute(String _dst, String _link) {
    Context.call(this.address, "addRoute", _dst, _link);
  }

  public void removeRoute(String _dst) {
    Context.call(this.address, "removeRoute", _dst);
  }

  public Map getRoutes() {
    return Context.call(Map.class, this.address, "getRoutes");
  }

  public BigInteger sendMessage(String _to, String _svc, BigInteger _sn, byte[] _msg) {
    return Context.call(BigInteger.class, this.address, "sendMessage", _to, _svc, _sn, _msg);
  }

  public BigInteger sendMessage(BigInteger valueForPayable, String _to, String _svc, BigInteger _sn,
      byte[] _msg) {
    return Context.call(BigInteger.class, valueForPayable, this.address, "sendMessage", _to, _svc, _sn, _msg);
  }

  public void handleRelayMessage(String _prev, String _msg) {
    Context.call(this.address, "handleRelayMessage", _prev, _msg);
  }

  public String getBtpAddress() {
    return Context.call(String.class, this.address, "getBtpAddress");
  }

  public String getNetworkAddress() {
    return Context.call(String.class, this.address, "getNetworkAddress");
  }

  public void setFeeTable(String[] _dst, BigInteger[][] _value) {
    Context.call(this.address, "setFeeTable", _dst, _value);
  }

  public BigInteger getFee(String _to, boolean _response) {
    return Context.call(BigInteger.class, this.address, "getFee", _to, _response);
  }

  public BigInteger[][] getFeeTable(String[] _dst) {
    return Context.call(BigInteger[][].class, this.address, "getFeeTable", _dst);
  }

  public void claimReward(String _network, String _receiver) {
    Context.call(this.address, "claimReward", _network, _receiver);
  }

  public void claimReward(BigInteger valueForPayable, String _network, String _receiver) {
    Context.call(valueForPayable, this.address, "claimReward", _network, _receiver);
  }

  /**
   * @deprecated Do not use this method, this is generated only for preventing compile error. not supported EventLog method
   * @throws java.lang.RuntimeException
   */
  @Deprecated
  public void ClaimReward(Address _sender, String _network, String _receiver, BigInteger _amount,
      BigInteger _nsn) {
    throw new RuntimeException("not supported EventLog method");
  }

  /**
   * @deprecated Do not use this method, this is generated only for preventing compile error. not supported EventLog method
   * @throws java.lang.RuntimeException
   */
  @Deprecated
  public void ClaimRewardResult(Address _sender, String _network, BigInteger _nsn,
      BigInteger _result) {
    throw new RuntimeException("not supported EventLog method");
  }

  public BigInteger getReward(String _network, Address _addr) {
    return Context.call(BigInteger.class, this.address, "getReward", _network, _addr);
  }

  public void setFeeHandler(Address _addr) {
    Context.call(this.address, "setFeeHandler", _addr);
  }

  public Address getFeeHandler() {
    return Context.call(Address.class, this.address, "getFeeHandler");
  }

  public BigInteger getNetworkSn() {
    return Context.call(BigInteger.class, this.address, "getNetworkSn");
  }

  /**
   * @deprecated Do not use this method, this is generated only for preventing compile error. not supported EventLog method
   * @throws java.lang.RuntimeException
   */
  @Deprecated
  public void Message(String _next, BigInteger _seq, byte[] _msg) {
    throw new RuntimeException("not supported EventLog method");
  }

  /**
   * @deprecated Do not use this method, this is generated only for preventing compile error. not supported EventLog method
   * @throws java.lang.RuntimeException
   */
  @Deprecated
  public void BTPEvent(String _src, BigInteger _nsn, String _next, String _event) {
    throw new RuntimeException("not supported EventLog method");
  }
}
