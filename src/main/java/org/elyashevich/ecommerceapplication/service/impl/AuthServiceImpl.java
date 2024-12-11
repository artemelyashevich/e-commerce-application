package org.elyashevich.ecommerceapplication.service.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerceapplication.dao.UserDao;
import org.elyashevich.ecommerceapplication.dao.impl.UserDaoImpl;
import org.elyashevich.ecommerceapplication.dto.AuthDto;
import org.elyashevich.ecommerceapplication.entity.Role;
import org.elyashevich.ecommerceapplication.entity.User;
import org.elyashevich.ecommerceapplication.exception.ResourceNotFoundException;
import org.elyashevich.ecommerceapplication.service.AuthService;
import org.elyashevich.ecommerceapplication.util.PasswordUtil;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthServiceImpl implements AuthService {

    private static final String SALT = "salt";
    private static final String ERROR_TEMPLATE = "User with email '%s' was not found.";

    @Getter
    private static final AuthServiceImpl instance = new AuthServiceImpl();

    private final UserDao userDao = UserDaoImpl.getInstance();

    @Override
    public AuthDto login(final User candidate) throws NoSuchAlgorithmException, InvalidKeySpecException {
        var user = this.userDao.findByEmail(candidate.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException(ERROR_TEMPLATE.formatted(candidate.getEmail())));
        if (!PasswordUtil.verifyPassword(candidate.getPassword(), SALT, 100, 15, user.getPassword())) {
            throw new RuntimeException("");
        }
        return AuthDto.builder()
                .id(user.getId())
                .role(user.getRole())
                .build();
    }


    @Override
    public AuthDto register(final User user) throws NoSuchAlgorithmException, InvalidKeySpecException {
        user.setPassword(PasswordUtil.hashPassword(user.getPassword(), SALT, 100, 15));
        user.setRole(Role.builder().name("USER").build());
        return AuthDto.builder()
                .id(this.userDao.create(user))
                .role(user.getRole())
                .build();
    }
}
