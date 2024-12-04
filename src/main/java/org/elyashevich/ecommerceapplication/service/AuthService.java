package org.elyashevich.ecommerceapplication.service;

import org.elyashevich.ecommerceapplication.entity.User;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface AuthService {

    Long login(final User user) throws NoSuchAlgorithmException, InvalidKeySpecException;

    Long register(final User user) throws NoSuchAlgorithmException, InvalidKeySpecException;
}
