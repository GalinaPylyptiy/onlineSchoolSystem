package util;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Security {
    private static final String hashAlgorithm = "SHA-256";
    public static String toHashPassword(String password){
        String hashedPassword = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(hashAlgorithm);
            byte[]digestedBytes = messageDigest.digest(password.getBytes(StandardCharsets.UTF_8));
            BigInteger bigInteger = new BigInteger(1,digestedBytes);
            hashedPassword = bigInteger.toString(16);

        }catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
      return hashedPassword;
    }
}



