package org.elyashevich.ecommerce.service;

import org.elyashevich.ecommerce.entity.User;

public interface UserService extends BaseService<User> {

    void defineRole(final Long id, final String roleName);

    void setImage(final Long id, final String filePath);
}
