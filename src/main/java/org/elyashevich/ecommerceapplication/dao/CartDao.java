package org.elyashevich.ecommerceapplication.dao;

import org.elyashevich.ecommerceapplication.entity.Cart;

import java.util.List;

public interface CartDao {

    void create(final Cart cart);

    List<Cart> findAllByUser(final Long userId);

    void delete(final Long id);

}
