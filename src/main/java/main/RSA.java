package main;

import java.math.BigInteger;

public class RSA {
    /**
     * Takes a string and converts each character to an ASCII decimal value
     * Returns BigInteger
     */
    public static BigInteger stringCipher(String message) {
        message = message.toUpperCase();
        StringBuilder cipherString = new StringBuilder();
        int i = 0;
        while (i < message.length()) {
            int ch = message.charAt(i);
            cipherString.append(ch);
            i++;
        }
        return new BigInteger(cipherString.toString());
    }


    /**
     * Takes a BigInteger that is ciphered and converts it back to plain text
     * returns a String
     */
    public static String cipherToString(BigInteger message) {
        String cipherString = message.toString();
        StringBuilder output = new StringBuilder();
        int i = 0;
        while (i < cipherString.length()) {
            int temp = Integer.parseInt(cipherString.substring(i, i + 2));
            char ch = (char) temp;
            output.append(ch);
            i = i + 2;
        }
        return output.toString().toLowerCase();
    }

    public static BigInteger encrypt(BigInteger message, RSAPublicKey publicKey) {
        return message.modPow(publicKey.getE(), publicKey.getN());
    }

    public static BigInteger decrypt(BigInteger message, RSAPrivateKey privateKey) {
        return message.modPow(privateKey.getD(), privateKey.getN());
    }

}
