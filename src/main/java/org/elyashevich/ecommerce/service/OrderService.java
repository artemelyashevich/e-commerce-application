package org.elyashevich.ecommerce.service;

import org.elyashevich.ecommerce.entity.Order;

import java.util.List;

public interface OrderService {
    void create(final Long userId);

    List<Order> findAll();

    void update(final Long id, final Order order);

    void delete(final Long id);
}
