package org.elyashevich.ecommerce.dao.impl;

import lombok.Getter;
import org.elyashevich.ecommerce.dao.AbstractDao;
import org.elyashevich.ecommerce.entity.Cart;

public class CartDaoImpl extends AbstractDao<Cart, Long> {

    @Getter
    private static final CartDaoImpl instance = new CartDaoImpl();

    private CartDaoImpl() {
        super(Cart.class);
    }

    @Override
    protected void setId(Cart entity, Long id) {
        entity.setId(id);
    }
}
