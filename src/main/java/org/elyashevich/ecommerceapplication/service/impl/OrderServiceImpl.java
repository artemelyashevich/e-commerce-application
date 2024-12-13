package org.elyashevich.ecommerceapplication.service.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerceapplication.dao.OrderDao;
import org.elyashevich.ecommerceapplication.dao.impl.OrderDaoImpl;
import org.elyashevich.ecommerceapplication.entity.Order;
import org.elyashevich.ecommerceapplication.service.OrderService;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderServiceImpl implements OrderService {

    @Getter
    private static final OrderServiceImpl instance = new OrderServiceImpl();

    private final OrderDao orderDao = OrderDaoImpl.getInstance();

    @Override
    public void create(final Order order) {
        this.orderDao.create(order);
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
