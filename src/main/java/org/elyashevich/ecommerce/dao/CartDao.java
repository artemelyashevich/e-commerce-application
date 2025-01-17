package org.elyashevich.ecommerce.dao;

import org.elyashevich.ecommerce.entity.Cart;

public interface CartDao extends GenericDao<Cart, Long> {

    void delete(Long productId, Long userId);
}
