package org.elyashevich.ecommerceapplication.dao;

import org.elyashevich.ecommerceapplication.entity.Cart;

import java.util.List;

public interface CartDao {

    void create(final Cart cart);

    void delete(final Long userId, final Long productId);

}
