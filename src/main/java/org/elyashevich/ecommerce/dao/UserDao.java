package org.elyashevich.ecommerce.dao;

import org.elyashevich.ecommerce.entity.Role;
import org.elyashevich.ecommerce.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao extends GenericDao<User, Long> {

    void defineRole(final Long id, final Role role);

    Optional<User> findByEmail(final String email);

    void setImage(final Long id, final String filePath);
}
