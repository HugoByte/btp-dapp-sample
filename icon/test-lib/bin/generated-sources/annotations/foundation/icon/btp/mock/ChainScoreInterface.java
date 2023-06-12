package foundation.icon.btp.mock;

import java.lang.Deprecated;
import java.lang.RuntimeException;
import java.lang.String;
import java.math.BigInteger;
import score.Address;
import score.Context;

public final class ChainScoreInterface implements ChainScore {
  protected final Address address;

  public ChainScoreInterface(Address address) {
    this.address = address;
  }

  public Address _address() {
    return this.address;
  }

  public void setRevision(int code) {
    Context.call(this.address, "setRevision", code);
  }

  public BigInteger getStepPrice() {
    return Context.call(BigInteger.class, this.address, "getStepPrice");
  }

  public BigInteger getStepCost(String type) {
    return Context.call(BigInteger.class, this.address, "getStepCost", type);
  }

  public void setMaxStepLimit(String contextType, BigInteger limit) {
    Context.call(this.address, "setMaxStepLimit", contextType, limit);
  }

  public BigInteger getMaxStepLimit(String contextType) {
    return Context.call(BigInteger.class, this.address, "getMaxStepLimit", contextType);
  }

  public long openBTPNetwork(String networkTypeName, String name, Address owner) {
    return Context.call(long.class, this.address, "openBTPNetwork", networkTypeName, name, owner);
  }

  /**
   * @deprecated Do not use this method, this is generated only for preventing compile error. not supported EventLog method
   * @throws java.lang.RuntimeException
   */
  @Deprecated
  public void BTPNetworkTypeActivated(String networkTypeName, long networkTypeID) {
    throw new RuntimeException("not supported EventLog method");
  }

  /**
   * @deprecated Do not use this method, this is generated only for preventing compile error. not supported EventLog method
   * @throws java.lang.RuntimeException
   */
  @Deprecated
  public void BTPNetworkOpened(long networkTypeID, long networkID) {
    throw new RuntimeException("not supported EventLog method");
  }

  public void closeBTPNetwork(long id) {
    Context.call(this.address, "closeBTPNetwork", id);
  }

  /**
   * @deprecated Do not use this method, this is generated only for preventing compile error. not supported EventLog method
   * @throws java.lang.RuntimeException
   */
  @Deprecated
  public void BTPNetworkClosed(long networkTypeID, long networkID) {
    throw new RuntimeException("not supported EventLog method");
  }

  public int getRevision() {
    return Context.call(int.class, this.address, "getRevision");
  }

  public void setBTPPublicKey(String name, byte[] pubKey) {
    Context.call(this.address, "setBTPPublicKey", name, pubKey);
  }

  public byte[] getBTPPublicKey(Address address, String name) {
    return Context.call(byte[].class, this.address, "getBTPPublicKey", address, name);
  }

  public long getBTPNetworkTypeID(String name) {
    return Context.call(long.class, this.address, "getBTPNetworkTypeID", name);
  }

  public void sendBTPMessage(long networkId, byte[] message) {
    Context.call(this.address, "sendBTPMessage", networkId, message);
  }
}
