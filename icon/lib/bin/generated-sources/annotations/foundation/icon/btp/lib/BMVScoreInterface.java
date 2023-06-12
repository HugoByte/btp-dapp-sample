package foundation.icon.btp.lib;

import java.lang.String;
import java.math.BigInteger;
import score.Address;
import score.Context;

public final class BMVScoreInterface implements BMV {
  protected final Address address;

  public BMVScoreInterface(Address address) {
    this.address = address;
  }

  public Address _address() {
    return this.address;
  }

  public byte[][] handleRelayMessage(String _bmc, String _prev, BigInteger _seq, byte[] _msg) {
    return Context.call(byte[][].class, this.address, "handleRelayMessage", _bmc, _prev, _seq, _msg);
  }

  public BMVStatus getStatus() {
    return Context.call(BMVStatus.class, this.address, "getStatus");
  }
}
