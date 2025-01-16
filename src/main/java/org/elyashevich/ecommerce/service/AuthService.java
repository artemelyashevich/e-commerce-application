package org.elyashevich.ecommerce.service;

import org.elyashevich.ecommerce.dto.AuthDto;
import org.elyashevich.ecommerce.entity.User;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface AuthService {

    AuthDto login(final User user) throws NoSuchAlgorithmException, InvalidKeySpecException;

    AuthDto register(final User user) throws NoSuchAlgorithmException, InvalidKeySpecException;
}
