package org.elyashevich.ecommerceapplication.service.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerceapplication.dao.OrderDao;
import org.elyashevich.ecommerceapplication.dao.impl.OrderDaoImpl;
import org.elyashevich.ecommerceapplication.entity.Order;
import org.elyashevich.ecommerceapplication.entity.Product;
import org.elyashevich.ecommerceapplication.service.CartService;
import org.elyashevich.ecommerceapplication.service.OrderService;
import org.elyashevich.ecommerceapplication.service.ProductService;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderServiceImpl implements OrderService {

    @Getter
    private static final OrderServiceImpl instance = new OrderServiceImpl();

    private final OrderDao orderDao = OrderDaoImpl.getInstance();
    private final ProductService productService = ProductServiceImpl.getInstance();
    private final CartService cartService = CartServiceImpl.getInstance();

    // TODO: add transactional logic
    @Override
    public void create(final Long userId) {
        var cartItems = this.productService.findFromCartByUser(userId);
        var totalAmount = cartItems.stream()
                .mapToDouble(Product::getPrice)
                .sum();
        var order = Order.builder()
                .userId(userId)
                .totalAmount(totalAmount)
                .build();
        this.orderDao.create(order);
        cartItems.forEach(i -> this.cartService.deleteProduct(userId, i.getId()));
    }

    @Override
    public List<Order> findAll() {
        return this.orderDao.findAll();
    }

    @Override
    public void update(final Long id, final Order order) {
        this.orderDao.update(id, order);
    }

    @Override
    public void delete(final Long id) {
        this.orderDao.delete(id);
    }
}
