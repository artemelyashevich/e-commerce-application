package org.elyashevich.ecommerceapplication.service;

import org.elyashevich.ecommerceapplication.dto.AuthDto;
import org.elyashevich.ecommerceapplication.entity.User;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface AuthService {

    AuthDto login(final User user) throws NoSuchAlgorithmException, InvalidKeySpecException;

    AuthDto register(final User user) throws NoSuchAlgorithmException, InvalidKeySpecException;
}
