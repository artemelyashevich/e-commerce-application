package org.elyashevich.ecommerceapplication.dao.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerceapplication.dao.ProductDao;
import org.elyashevich.ecommerceapplication.entity.Product;
import org.elyashevich.ecommerceapplication.exception.DaoException;
import org.elyashevich.ecommerceapplication.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductDaoImpl implements ProductDao {

    @Getter
    private static final ProductDaoImpl instance = new ProductDaoImpl();

    @Override
    public void create(Product product) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.save(product);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw new DaoException("Error creating product: " + e.getMessage());
            }
        }
    }

    @Override
    public List<Product> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Product", Product.class).list();
        } catch (Exception e) {
            throw new DaoException("Error finding all products: " + e.getMessage());
        }
    }

    @Override
    public void update(Long id, Product product) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                product.setId(id);
                session.update(product);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw new DaoException("Error updating product: " + e.getMessage());
            }
        }
    }

    @Override
    public void delete(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Product product = session.load(Product.class, id);
                session.delete(product);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw new DaoException("Error deleting product: " + e.getMessage());
            }
        }
    }

    @Override
    public List<Product> findFromCart(Long userId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Product p JOIN p.cart c WHERE c.userId = :userId", Product.class)
                    .setParameter("userId", userId)
                    .list();
        } catch (Exception e) {
            throw new DaoException("Error finding products from cart: " + e.getMessage());
        }
    }

    @Override
    public void setImage(Long id, String filePath) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
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
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Product p WHERE p.categoryId = :categoryId", Product.class)
                    .setParameter("categoryId", categoryId)
                    .list();
        } catch (Exception e) {
            throw new DaoException("Error finding products by category ID: " + e.getMessage());
        }
    }

    @Override
    public List<Product> findByQuery(String query) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Product p WHERE p.name LIKE :query", Product.class)
                    .setParameter("query", "%" + query + "%")
                    .list();
        } catch (Exception e) {
            throw new DaoException("Error finding products by query: " + e.getMessage());
        }
    }

    @Override
    public Product findById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Product.class, id);
        } catch (Exception e) {
            throw new DaoException("Error finding product by ID: " + e.getMessage());
        }
    }
}
