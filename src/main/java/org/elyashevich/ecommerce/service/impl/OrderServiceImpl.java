package org.elyashevich.ecommerce.service.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elyashevich.ecommerce.dao.OrderDao;
import org.elyashevich.ecommerce.dao.impl.OrderDaoImpl;
import org.elyashevich.ecommerce.entity.Order;
import org.elyashevich.ecommerce.entity.Product;
import org.elyashevich.ecommerce.service.CartService;
import org.elyashevich.ecommerce.service.OrderService;
import org.elyashevich.ecommerce.service.ProductService;

import java.util.List;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderServiceImpl implements OrderService {

    @Getter
    private static final OrderServiceImpl instance = new OrderServiceImpl();

    private final OrderDao orderDao = OrderDaoImpl.getInstance();
    private final ProductService productService = ProductServiceImpl.getInstance();
    private final CartService cartService = CartServiceImpl.getInstance();

    @Override
    public void create(final Long userId) {
        log.info("Attempting user with id: '{}' make order", userId);

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

        log.info("User with id: '{}' made order", userId);
    }

    @Override
    public List<Order> findAll() {
        log.info("Attempting to find all orders");

        var orders = this.orderDao.findAll();

        log.info("All orders has been found");
        return orders;
    }

    @Override
    public void update(final Long id, final Order order) {
        log.info("Attempting to update order with id: {}", id);

        this.orderDao.update(id, order);

        log.info("Order with id: '{}' has been updated", id);
    }

    @Override
    public void delete(final Long id) {
        log.info("Attempting to delete order with id: {}", id);

        this.orderDao.delete(id);

        log.info("Order with id: '{}' has been deleted", id);
    }
}
