package main;

import java.math.BigInteger;
import java.util.Random;

/**
 * @author Sasha
 */
public class RSAKeyGen {
    private BigInteger n;
    private BigInteger e;
    private BigInteger d;

    public RSAKeyGen(int len) {
        // 1. Find large primes p and q
        BigInteger p = largePrime(len);
        BigInteger q = largePrime(len);

        // 2. Compute n from p and q
        // n is mod for private & public keys, n bit length is key length
        this.n = n(p, q);

        // 3. Compute Phi(n) (Euler's totient function)
        // Phi(n) = (p-1)(q-1)
        // BigIntegers are objects and must use methods for algebraic operations
        BigInteger phi = getPhi(p, q);

        // 4. Find an int e such that 1 < e < Phi(n) 	and gcd(e,Phi) = 1
        this.e = genE(phi);

        // 5. Calculate d where  d ≡ e^(-1) (mod Phi(n))
        this.d = extEuclid(e, phi)[1];
    }

    public RSAPrivateKey getPrivateKey() {
        return new RSAPrivateKey(d, n);
    }

    public RSAPublicKey getPublicKey() {
        return new RSAPublicKey(e, n);
    }

    /**
     * Generates a random large prime number of specified bitlength
     */
    public static BigInteger largePrime(int bits) {
        Random randomInteger = new Random();
        return BigInteger.probablePrime(bits, randomInteger);
    }


    /**
     * Recursive EXTENDED Euclidean algorithm, solves Bezout's identity (ax + by = gcd(a,b))
     * and finds the multiplicative inverse which is the solution to ax ≡ 1 (mod m)
     * returns [d, p, q] where d = gcd(a,b) and ap + bq = d
     * Note: Uses BigInteger operations
     */
    public static BigInteger[] extEuclid(BigInteger a, BigInteger b) {
        if (b.equals(BigInteger.ZERO)) return new BigInteger[] {
                a, BigInteger.ONE, BigInteger.ZERO
        }; // { a, 1, 0 }
        BigInteger[] vals = extEuclid(b, a.mod(b));
        BigInteger d = vals[0];
        BigInteger p = vals[2];
        BigInteger q = vals[1].subtract(a.divide(b).multiply(vals[2]));
        return new BigInteger[] {
                d, p, q
        };
    }


    /**
     * generate e by finding a Phi such that they are coprimes (gcd = 1)
     */
    public static BigInteger genE(BigInteger phi) {
        Random rand = new Random();
        BigInteger e = new BigInteger(1024, rand);
        do {
            e = new BigInteger(1024, rand);
            while (e.min(phi).equals(phi)) { // while phi is smaller than e, look for a new e
                e = new BigInteger(1024, rand);
            }
        } while (!gcd(e, phi).equals(BigInteger.ONE)); // if gcd(e,phi) isnt 1 then stay in loop
        return e;
    }

    public static BigInteger n(BigInteger p, BigInteger q) {
        return p.multiply(q);

    }

    /**
     * Recursive implementation of Euclidian algorithm to find greatest common denominator
     * Note: Uses BigInteger operations
     */
    public static BigInteger gcd(BigInteger a, BigInteger b) {
        if (b.equals(BigInteger.ZERO)) {
            return a;
        } else {
            return gcd(b, a.mod(b));
        }
    }

    /**
     * 3. Compute Phi(n) (Euler's totient function)
     * Phi(n) = (p-1)(q-1)
     * BigIntegers are objects and must use methods for algebraic operations
     */
    public static BigInteger getPhi(BigInteger p, BigInteger q) {
        return (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
    }
}
