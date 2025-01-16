package org.elyashevich.ecommerce.service;

import org.elyashevich.ecommerce.entity.Cart;

public interface CartService {

    void addProduct(final Cart cart);

    void deleteProduct(final Long userId, final Long productId);


}
