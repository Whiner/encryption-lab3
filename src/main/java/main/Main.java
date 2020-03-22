package main;

import java.math.BigInteger;

/**
 * @author Sasha
 */
public class Main {

    public static void main(String[] args) throws Exception {
        RSAKeyGen rsaKeyGen = new RSAKeyGen(512);

        // encryption / decryption example
        String message = FileIO.read("original.txt");
        // Convert string to numbers using a cipher
        BigInteger cipherMessage = RSA.stringCipher(message);
        // Encrypt the ciphered message
        BigInteger encrypted = RSA.encrypt(cipherMessage, rsaKeyGen.getPublicKey());

        FileIO.write(encrypted.toString(), "encrypted.txt");

        String read = FileIO.read("encrypted.txt");

        // Decrypt the encrypted message
        BigInteger decrypted = RSA.decrypt(new BigInteger(read), rsaKeyGen.getPrivateKey());
        // Uncipher the decrypted message to text
        String restoredMessage = RSA.cipherToString(decrypted);

        FileIO.write(restoredMessage, "decrypted.txt");

        System.out.println("Original message: " + message);
        System.out.println("Ciphered: " + cipherMessage);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);
        System.out.println("Restored: " + restoredMessage);
    }




}
