package org.elyashevich.ecommerceapplication.service.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerceapplication.dao.CartDao;
import org.elyashevich.ecommerceapplication.dao.impl.CartDaoImpl;
import org.elyashevich.ecommerceapplication.entity.Cart;
import org.elyashevich.ecommerceapplication.service.CartService;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CartServiceImpl implements CartService {

    @Getter
    private static final CartServiceImpl instance = new CartServiceImpl();

    private final CartDao cartDao = CartDaoImpl.getInstance();

    @Override
    public List<Cart> findAllByUser(final Long userId) {
        return this.cartDao.findAllByUser(userId);
    }

    @Override
    public void addProduct(final Cart cart) {
        this.cartDao.create(cart);
    }

    @Override
    public void deleteProduct(final Long userId, final Long productId) {
        this.cartDao.delete(userId, productId);
    }
}
