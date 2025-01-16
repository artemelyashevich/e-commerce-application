package org.elyashevich.ecommerce.dao.impl;

import lombok.Getter;
import org.elyashevich.ecommerce.dao.AbstractDao;
import org.elyashevich.ecommerce.dao.RoleDao;
import org.elyashevich.ecommerce.dao.UserDao;
import org.elyashevich.ecommerce.entity.Role;
import org.elyashevich.ecommerce.entity.User;
import org.elyashevich.ecommerce.exception.DaoException;
import org.elyashevich.ecommerce.config.HibernateSessionFactorySingleton;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Optional;

public class UserDaoImpl extends AbstractDao<User, Long> implements UserDao {

    @Getter
    private static final UserDaoImpl instance = new UserDaoImpl();

    private final RoleDao roleDao = RoleDaoImpl.getInstance();

    private UserDaoImpl() {
        super(User.class);
    }

    @Override
    public User create(User user) {
        try (Session session = HibernateSessionFactorySingleton.getInstance().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Long userId = (Long) session.save(user);
                transaction.commit();
                Role role = roleDao.findByName(user.getRole().getName());
                this.defineRole(userId, role);
                return user;
            } catch (Exception e) {
                transaction.rollback();
                throw new DaoException("Error creating user: " + e.getMessage());
            }
        }
    }

    @Override
    protected void setId(User entity, Long id) {
        entity.setId(id);
    }

    @Override
    public void defineRole(Long userId, Role role) {
        try (Session session = HibernateSessionFactorySingleton.getInstance().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                User user = session.load(User.class, userId);
                user.setRole(role);
                session.update(user);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw new DaoException("Error defining role for user: " + e.getMessage());
            }
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (Session session = HibernateSessionFactorySingleton.getInstance().openSession()) {
            User user = session.createQuery("FROM User u JOIN FETCH u.role WHERE u.email = :email", User.class)
                    .setParameter("email", email)
                    .uniqueResult();
            return Optional.ofNullable(user);
        } catch (Exception e) {
            throw new DaoException("Error finding user by email: " + e.getMessage());
        }
    }

    @Override
    public void setImage(Long id, String filePath) {
        try (Session session = HibernateSessionFactorySingleton.getInstance().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                User user = session.load(User.class, id);
                user.setImage(filePath);
                session.update(user);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw new DaoException("Error setting image for user: " + e.getMessage());
            }
        }
    }
}
