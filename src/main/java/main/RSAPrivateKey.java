package main;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * @author Sasha
 */
public class RSAPrivateKey implements Serializable {
    private BigInteger d;
    private BigInteger n;

    public RSAPrivateKey(BigInteger d, BigInteger n) {
        this.d = d;
        this.n = n;
    }

    public BigInteger getD() {
        return d;
    }

    public BigInteger getN() {
        return n;
    }
}
