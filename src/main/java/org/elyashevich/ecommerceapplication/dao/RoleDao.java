package org.elyashevich.ecommerceapplication.dao;

import org.elyashevich.ecommerceapplication.entity.Role;

public interface RoleDao extends BaseDao<Role> {

    Role findByName(final String name);
}
