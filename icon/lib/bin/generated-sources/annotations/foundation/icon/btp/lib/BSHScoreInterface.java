package foundation.icon.btp.lib;

import java.lang.String;
import java.math.BigInteger;
import score.Address;
import score.Context;

public final class BSHScoreInterface implements BSH {
  protected final Address address;

  public BSHScoreInterface(Address address) {
    this.address = address;
  }

  public Address _address() {
    return this.address;
  }

  public void handleBTPMessage(String _from, String _svc, BigInteger _sn, byte[] _msg) {
    Context.call(this.address, "handleBTPMessage", _from, _svc, _sn, _msg);
  }

  public void handleBTPError(String _src, String _svc, BigInteger _sn, long _code, String _msg) {
    Context.call(this.address, "handleBTPError", _src, _svc, _sn, _code, _msg);
  }
}
