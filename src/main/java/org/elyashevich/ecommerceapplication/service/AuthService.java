package org.elyashevich.ecommerceapplication.service;

import org.elyashevich.ecommerceapplication.entity.User;

public interface AuthService {

    void login(final User user);

    void register(final User user);
}
