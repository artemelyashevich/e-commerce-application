package org.elyashevich.ecommerce.dao;

import org.elyashevich.ecommerce.entity.Role;
import org.elyashevich.ecommerce.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    List<User> findAll();

    void update(final Long id, final User user);

    void delete(final Long id);

    Long create(final User user);

    void defineRole(final Long id, final Role role);

    Optional<User> findByEmail(final String email);

    void setImage(final Long id, final String filePath);
}
