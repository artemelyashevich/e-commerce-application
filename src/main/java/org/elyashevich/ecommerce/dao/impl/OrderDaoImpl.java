package org.elyashevich.ecommerce.dao.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerce.dao.AbstractDao;
import org.elyashevich.ecommerce.dao.OrderDao;
import org.elyashevich.ecommerce.entity.Order;
import org.elyashevich.ecommerce.exception.DaoException;
import org.elyashevich.ecommerce.config.HibernateSessionFactorySingleton;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class OrderDaoImpl extends AbstractDao<Order, Long> implements OrderDao {

    @Getter
    private static final OrderDaoImpl instance = new OrderDaoImpl();

    private OrderDaoImpl() {
        super(Order.class);
    }

    @Override
    protected void setId(Order entity, Long id) {
        entity.setId(id);
    }
}
