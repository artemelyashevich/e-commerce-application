package org.elyashevich.ecommerceapplication.service;

import org.elyashevich.ecommerceapplication.entity.Cart;

import java.util.List;

public interface CartService {

    List<Cart> findAllByUser(final Long userId);

    void addProduct(final Cart cart);

    void deleteProduct(final Long userId, final Long productId);
}
