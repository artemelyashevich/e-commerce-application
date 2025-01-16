package org.elyashevich.ecommerce.service.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elyashevich.ecommerce.dao.RoleDao;
import org.elyashevich.ecommerce.dao.UserDao;
import org.elyashevich.ecommerce.dao.impl.RoleDaoImpl;
import org.elyashevich.ecommerce.dao.impl.UserDaoImpl;
import org.elyashevich.ecommerce.entity.User;
import org.elyashevich.ecommerce.service.UserService;

import java.util.List;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService {

    @Getter
    private static final UserServiceImpl instance = new UserServiceImpl();

    private final UserDao userDao = UserDaoImpl.getInstance();
    private final RoleDao roleDao = RoleDaoImpl.getInstance();

    @Override
    public void create(final User user) {
        log.info("Attempting to create new user: {}", user);

        this.userDao.create(user);

        log.info("User has been created: {}", user);
    }

    @Override
    public List<User> findAll() {
        log.info("Attempting to find all users");

        var users = this.userDao.findAll();

        log.info("All users have been found.");
        return users;
    }

    @Override
    public void update(final Long id, final User user) {
        log.info("Attempting to update user with id: {}", id);

        this.userDao.update(id, user);

        log.info("User with id '{}' has been updated", id);
    }

    @Override
    public void delete(final Long id) {
        log.info("Attempting to delete user with id: {}", id);

        this.userDao.delete(id);

        log.info("User with id '{}' has been deleted", id);
    }

    @Override
    public void defineRole(final Long id, final String roleName) {
        log.info("Attempting to set role: '{}' to user with id: {}", roleName, id);

        var role = this.roleDao.findByName(roleName);
        this.userDao.defineRole(id, role);

        log.info("Role: '{}' has been set to user with id: {}", roleName, id);
    }

    @Override
    public void setImage(final Long id, final String filePath) {
        log.info("Attempting to set image to user with id: {}", id);

        this.userDao.setImage(id, filePath);

        log.info("Image has been set to user with id: {}", id);
    }
}
