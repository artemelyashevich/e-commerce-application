package org.elyashevich.ecommerce.dao;

import org.elyashevich.ecommerce.entity.Role;

public interface RoleDao extends GenericDao<Role, Long> {

    Role findByName(final String name);
}
