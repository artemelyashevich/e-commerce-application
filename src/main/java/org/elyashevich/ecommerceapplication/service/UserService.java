package org.elyashevich.ecommerceapplication.service;

import org.elyashevich.ecommerceapplication.entity.User;

public interface UserService extends BaseService<User> {

    void defineRole(final Long id, final String roleName);

    void setImage(final Long id, final String filePath);
}
