package org.elyashevich.ecommerce.service.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elyashevich.ecommerce.dao.UserDao;
import org.elyashevich.ecommerce.dao.impl.UserDaoImpl;
import org.elyashevich.ecommerce.dto.AuthDto;
import org.elyashevich.ecommerce.entity.Role;
import org.elyashevich.ecommerce.entity.User;
import org.elyashevich.ecommerce.exception.PasswordMismatchException;
import org.elyashevich.ecommerce.exception.ResourceNotFoundException;
import org.elyashevich.ecommerce.service.AuthService;
import org.elyashevich.ecommerce.util.PasswordUtil;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthServiceImpl implements AuthService {

    private static final String SALT = "salt";
    private static final String ERROR_TEMPLATE = "User with email '%s' was not found.";

    @Getter
    private static final AuthServiceImpl instance = new AuthServiceImpl();

    private final UserDao userDao = UserDaoImpl.getInstance();

    @Override
    public AuthDto login(final User candidate) throws NoSuchAlgorithmException, InvalidKeySpecException {
        log.info("Attempting to authenticate user: {}", candidate);

        var user = this.userDao.findByEmail(candidate.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException(ERROR_TEMPLATE.formatted(candidate.getEmail())));
        if (!PasswordUtil.verifyPassword(candidate.getPassword(), SALT, 100, 15, user.getPassword())) {
            log.error("Failed to authenticate user");

            throw new PasswordMismatchException("Invalid password");
        }
        var authDto = AuthDto.builder()
                .id(user.getId())
                .role(user.getRole())
                .build();

        log.info("User has been authenticated: {}", authDto);
        return authDto;
    }


    @Override
    public AuthDto register(final User user) throws NoSuchAlgorithmException, InvalidKeySpecException {
        log.info("Attempting to register user: {}", user);

        user.setPassword(PasswordUtil.hashPassword(user.getPassword(), SALT, 100, 15));
        user.setRole(Role.builder().name("USER").build());
        var authDto = AuthDto.builder()
                .id(this.userDao.create(user))
                .role(user.getRole())
                .build();

        log.info("User has been registered: {}", authDto);
        return authDto;
    }
}
