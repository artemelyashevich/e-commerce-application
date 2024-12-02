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
        var hash = factory.generateSecret(spec).getEncoded();
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
        return storedHash.equals(hashedPassword);
    }
}
