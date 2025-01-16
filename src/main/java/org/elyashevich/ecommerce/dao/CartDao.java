package org.elyashevich.ecommerce.dao;

import org.elyashevich.ecommerce.entity.Cart;

public interface CartDao {

    void create(final Cart cart);

    void delete(final Long userId, final Long productId);

}
