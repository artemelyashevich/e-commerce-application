package org.elyashevich.ecommerce.dao.impl;

import lombok.Getter;
import org.elyashevich.ecommerce.dao.AbstractDao;
import org.elyashevich.ecommerce.dao.ProductDao;
import org.elyashevich.ecommerce.entity.Product;
import org.elyashevich.ecommerce.exception.DaoException;
import org.elyashevich.ecommerce.config.HibernateSessionFactorySingleton;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.OptimisticLockException;
import java.util.List;

public class ProductDaoImpl extends AbstractDao<Product, Long> implements ProductDao {

    @Getter
    private static final ProductDaoImpl instance = new ProductDaoImpl();

    private ProductDaoImpl() {
        super(Product.class);
    }

    @Override
    public List<Product> findFromCart(Long userId) {
        try (Session session = HibernateSessionFactorySingleton.getInstance().openSession()) {
            return session.createQuery("FROM Product p JOIN p.cart c WHERE c.userId = :userId", Product.class)
                    .setParameter("userId", userId)
                    .list();
        } catch (Exception e) {
            throw new DaoException("Error finding products from cart: " + e.getMessage());
        }
    }

    @Override
    public void setImage(Long id, String filePath) {
        try (Session session = HibernateSessionFactorySingleton.getInstance().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Product product = session.load(Product.class, id);
                product.setImage(filePath);
                session.update(product);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw new DaoException("Error setting image for product: " + e.getMessage());
            }
        }
    }

    @Override
    public List<Product> findByCategoryId(Long categoryId) {
        try (Session session = HibernateSessionFactorySingleton.getInstance().openSession()) {
            return session.createQuery("FROM Product p WHERE p.categoryId = :categoryId", Product.class)
                    .setParameter("categoryId", categoryId)
                    .list();
        } catch (Exception e) {
            throw new DaoException("Error finding products by category ID: " + e.getMessage());
        }
    }

    @Override
    public List<Product> findByQuery(String query) {
        try (Session session = HibernateSessionFactorySingleton.getInstance().openSession()) {
            return session.createQuery("FROM Product p WHERE p.name LIKE :query", Product.class)
                    .setParameter("query", "%" + query + "%")
                    .list();
        } catch (Exception e) {
            throw new DaoException("Error finding products by query: " + e.getMessage());
        }
    }

    @Override
    public Product findById(Long id) {
        try (Session session = HibernateSessionFactorySingleton.getInstance().openSession()) {
            return session.get(Product.class, id);
        } catch (Exception e) {
            throw new DaoException("Error finding product by ID: " + e.getMessage());
        }
    }

    @Override
    public void update(Long id, Product entity) {
        try {
            super.update(id, entity);
        } catch (OptimisticLockException e) {
            throw new DaoException("Update failed: another transaction has updated the data.", e);
        }
    }

    @Override
    protected void setId(Product entity, Long id) {
        entity.setId(id);
    }
}
