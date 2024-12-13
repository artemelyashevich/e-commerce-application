package org.elyashevich.ecommerceapplication.service.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elyashevich.ecommerceapplication.dao.CartDao;
import org.elyashevich.ecommerceapplication.dao.impl.CartDaoImpl;
import org.elyashevich.ecommerceapplication.entity.Cart;
import org.elyashevich.ecommerceapplication.service.CartService;

import java.util.List;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CartServiceImpl implements CartService {

    @Getter
    private static final CartServiceImpl instance = new CartServiceImpl();

    private final CartDao cartDao = CartDaoImpl.getInstance();

    @Override
    public void addProduct(final Cart cart) {
        log.info("Attempting to add product into cart: {}", cart);

        this.cartDao.create(cart);

        log.info("Product has been added into cart: {}", cart);
    }

    @Override
    public void deleteProduct(final Long userId, final Long productId) {
        log.info("Attempting to delete product from cart: {}", productId);

        this.cartDao.delete(userId, productId);

        log.info("Product has been deleted from cart: {}", productId);
    }
}
