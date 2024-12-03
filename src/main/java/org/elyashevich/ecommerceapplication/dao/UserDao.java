package org.elyashevich.ecommerceapplication.dao;

import org.elyashevich.ecommerceapplication.entity.Role;
import org.elyashevich.ecommerceapplication.entity.User;

import java.util.Optional;

public interface UserDao extends BaseDao<User> {

    void defineRole(final Long id, final Role role);

    Optional<User> findByEmail(final String email);
}
