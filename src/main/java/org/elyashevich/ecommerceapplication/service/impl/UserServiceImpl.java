package org.elyashevich.ecommerceapplication.service.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerceapplication.dao.RoleDao;
import org.elyashevich.ecommerceapplication.dao.UserDao;
import org.elyashevich.ecommerceapplication.dao.impl.RoleDaoImpl;
import org.elyashevich.ecommerceapplication.dao.impl.UserDaoImpl;
import org.elyashevich.ecommerceapplication.entity.User;
import org.elyashevich.ecommerceapplication.service.UserService;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService {

    @Getter
    private static final UserServiceImpl instance = new UserServiceImpl();

    private final UserDao userDao = UserDaoImpl.getInstance();
    private final RoleDao roleDao = RoleDaoImpl.getInstance();

    @Override
    public void create(final User user) {

    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void update(final Long id, final User user) {

    }

    @Override
    public void delete(final Long id) {

    }

    @Override
    public void defineRole(final Long id, final String roleName) {

    }
}
