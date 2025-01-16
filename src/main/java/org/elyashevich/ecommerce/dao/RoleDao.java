package org.elyashevich.ecommerce.dao;

import org.elyashevich.ecommerce.entity.Role;

public interface RoleDao extends BaseDao<Role> {

    Role findByName(final String name);
}
