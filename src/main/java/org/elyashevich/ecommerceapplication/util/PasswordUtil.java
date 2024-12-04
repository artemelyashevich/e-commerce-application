package org.elyashevich.ecommerceapplication.util;

import lombok.experimental.UtilityClass;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

@UtilityClass
public class PasswordUtil {

    public static String hashPassword(
            final String password,
            final String salt,
            final int iterations,
            final int keyLength
    ) throws NoSuchAlgorithmException, InvalidKeySpecException {
        var spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), iterations, keyLength);
        var factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = factory.generateSecret(spec).getEncoded();
        return Base64.getEncoder().encodeToString(hash);
    }

    public static boolean verifyPassword(
            final String password,
            final String salt,
            final int iterations,
            final int keyLength,
            final String storedHash
    ) throws NoSuchAlgorithmException, InvalidKeySpecException {
        var hashedPassword = hashPassword(password, salt, iterations, keyLength);
        return slowEquals(storedHash.getBytes(), hashedPassword.getBytes());
    }

    private static boolean slowEquals(byte[] a, byte[] b) {
        int diff = a.length ^ b.length;
        for (int i = 0; i < a.length && i < b.length; i++) {
            diff |= a[i] ^ b[i];
        }
        return diff == 0;
    }

}
