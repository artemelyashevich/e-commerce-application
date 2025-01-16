package org.elyashevich.ecommerce.dao.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerce.dao.AbstractDao;
import org.elyashevich.ecommerce.dao.RoleDao;
import org.elyashevich.ecommerce.entity.Role;
import org.elyashevich.ecommerce.exception.DaoException;
import org.elyashevich.ecommerce.config.HibernateSessionFactorySingleton;
import org.hibernate.Session;

public class RoleDaoImpl extends AbstractDao<Role, Long> implements RoleDao {

    @Getter
    private static final RoleDaoImpl instance = new RoleDaoImpl();

    private RoleDaoImpl() {
        super(Role.class);
    }

    @Override
    public Role findByName(String name) {
        try (Session session = HibernateSessionFactorySingleton.getInstance().openSession()) {
            return session.createQuery("FROM Role r WHERE r.name = :name", Role.class)
                    .setParameter("name", name)
                    .uniqueResult();
        } catch (Exception e) {
            throw new DaoException("Error finding role by name: " + e.getMessage());
        }
    }

    @Override
    protected void setId(Role entity, Long id) {
        entity.setId(id);
    }
}
