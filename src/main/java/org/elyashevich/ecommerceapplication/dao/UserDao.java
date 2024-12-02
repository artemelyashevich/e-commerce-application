package org.elyashevich.ecommerceapplication.dao;

import org.elyashevich.ecommerceapplication.entity.Role;
import org.elyashevich.ecommerceapplication.entity.User;

public interface UserDao extends BaseDao<User> {

    void defineRole(final Long id, final Role role);
}
