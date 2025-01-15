package org.elyashevich.ecommerceapplication.dao.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerceapplication.dao.RoleDao;
import org.elyashevich.ecommerceapplication.dao.UserDao;
import org.elyashevich.ecommerceapplication.entity.Role;
import org.elyashevich.ecommerceapplication.entity.User;
import org.elyashevich.ecommerceapplication.exception.DaoException;
import org.elyashevich.ecommerceapplication.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDaoImpl implements UserDao {

    @Getter
    private static final UserDaoImpl instance = new UserDaoImpl();

    private final RoleDao roleDao = RoleDaoImpl.getInstance();

    @Override
    public Long create(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Long userId = (Long) session.save(user);
                transaction.commit();
                Role role = roleDao.findByName(user.getRole().getName());
                this.defineRole(userId, role);
                return userId;
            } catch (Exception e) {
                transaction.rollback();
                throw new DaoException("Error creating user: " + e.getMessage());
            }
        }
    }

    @Override
    public List<User> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM User u JOIN FETCH u.role", User.class).list();
        } catch (Exception e) {
            throw new DaoException("Error finding all users: " + e.getMessage());
        }
    }

    @Override
    public void update(Long id, User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                user.setId(id);
                session.update(user);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw new DaoException("Error updating user: " + e.getMessage());
            }
        }
    }

    @Override
    public void delete(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                User user = session.load(User.class, id);
                session.delete(user);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw new DaoException("Error deleting user: " + e.getMessage());
            }
        }
    }

    @Override
    public void defineRole(Long userId, Role role) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
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
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
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
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
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
