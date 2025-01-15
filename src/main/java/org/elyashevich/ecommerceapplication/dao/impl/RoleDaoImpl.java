package org.elyashevich.ecommerceapplication.dao.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerceapplication.dao.RoleDao;
import org.elyashevich.ecommerceapplication.entity.Role;
import org.elyashevich.ecommerceapplication.exception.DaoException;
import org.elyashevich.ecommerceapplication.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RoleDaoImpl implements RoleDao {

    @Getter
    private static final RoleDaoImpl instance = new RoleDaoImpl();

    @Override
    public void create(Role role) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.save(role);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw new DaoException("Error creating role: " + e.getMessage());
            }
        }
    }

    @Override
    public List<Role> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Role", Role.class).list();
        } catch (Exception e) {
            throw new DaoException("Error finding all roles: " + e.getMessage());
        }
    }

    @Override
    public void update(Long id, Role role) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                role.setId(id);
                session.update(role);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw new DaoException("Error updating role: " + e.getMessage());
            }
        }
    }

    @Override
    public void delete(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Role role = session.load(Role.class, id);
                session.delete(role);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw new DaoException("Error deleting role: " + e.getMessage());
            }
        }
    }

    @Override
    public Role findByName(String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Role r WHERE r.name = :name", Role.class)
                    .setParameter("name", name)
                    .uniqueResult();
        } catch (Exception e) {
            throw new DaoException("Error finding role by name: " + e.getMessage());
        }
    }
}
