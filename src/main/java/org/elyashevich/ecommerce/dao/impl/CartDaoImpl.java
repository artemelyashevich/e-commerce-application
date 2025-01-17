package org.elyashevich.ecommerce.dao.impl;

import lombok.Getter;
import org.elyashevich.ecommerce.dao.AbstractDao;
import org.elyashevich.ecommerce.dao.CartDao;
import org.elyashevich.ecommerce.entity.Cart;

public class CartDaoImpl extends AbstractDao<Cart, Long> implements CartDao {

    @Getter
    private static final CartDaoImpl instance = new CartDaoImpl();

    private CartDaoImpl() {
        super(Cart.class);
    }

    @Override
    protected void setId(Cart entity, Long id) {
        entity.setId(id);
    }

    @Override
    public void delete(Long productId, Long userId) {}
}
