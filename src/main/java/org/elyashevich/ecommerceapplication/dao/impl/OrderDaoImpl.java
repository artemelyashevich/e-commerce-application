package org.elyashevich.ecommerceapplication.dao.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerceapplication.dao.OrderDao;
import org.elyashevich.ecommerceapplication.entity.Order;
import org.elyashevich.ecommerceapplication.exception.DaoException;
import org.elyashevich.ecommerceapplication.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderDaoImpl implements OrderDao {

    @Getter
    private static final OrderDaoImpl instance = new OrderDaoImpl();

    @Override
    public void create(final Order order) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.save(order);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw new DaoException("Error creating order: " + e.getMessage());
            }
        }
    }

    @Override
    public List<Order> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Order", Order.class).list();
        } catch (Exception e) {
            throw new DaoException("Error finding all orders: " + e.getMessage());
        }
    }

    @Override
    public void update(final Long id, final Order order) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                order.setId(id);
                session.update(order);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw new DaoException("Error updating order: " + e.getMessage());
            }
        }
    }

    @Override
    public void delete(final Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Order order = session.load(Order.class, id);
                session.delete(order);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw new DaoException("Error deleting order: " + e.getMessage());
            }
        }
    }
}
