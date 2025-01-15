package org.elyashevich.ecommerceapplication.dao.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerceapplication.dao.CartDao;
import org.elyashevich.ecommerceapplication.entity.Cart;
import org.elyashevich.ecommerceapplication.exception.DaoException;
import org.elyashevich.ecommerceapplication.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CartDaoImpl implements CartDao {

    @Getter
    private static final CartDaoImpl instance = new CartDaoImpl();

    private static final String DELETE_QUERY = "DELETE FROM Cart WHERE userId = :userId AND productId = :productId";

    @Override
    public void create(final Cart cart) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(cart);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException("Error occurred while adding product to cart: " + e.getMessage());
        }
    }

    @Override
    public void delete(final Long userId, final Long productId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createQuery(DELETE_QUERY)
                    .setParameter("userId", userId)
                    .setParameter("productId", productId)
                    .executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException("Error occurred while removing product from cart: " + e.getMessage());
        }
    }
}
