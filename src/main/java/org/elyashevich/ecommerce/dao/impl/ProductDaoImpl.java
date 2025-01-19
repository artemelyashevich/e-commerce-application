package org.elyashevich.ecommerce.dao.impl;

import lombok.Getter;
import org.elyashevich.ecommerce.dao.AbstractDao;
import org.elyashevich.ecommerce.dao.CategoryDao;
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

    private final CategoryDao categoryDao = CategoryDaoImpl.getInstance();

    private ProductDaoImpl() {
        super(Product.class);
    }

    @Override
    public Product create(Product product) throws DaoException {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                var category = this.categoryDao.findById(product.getCategory().getId());
                product.setCategory(category);
                session.save(product);
                transaction.commit();
                return product;
            } catch (Exception e) {
                transaction.rollback();
                throw new DaoException("Error creating new product: " + e.getMessage());
            }
        } catch (Exception e) {
            throw new DaoException("Error creating new product: " + e.getMessage());
        }
    }

    @Override
    public List<Product> findFromCart(Long userId) {
        try (Session session = sessionFactory.openSession()) {
            return (List<Product>) session
                    .createQuery("SELECT p FROM Product p JOIN Cart c on c.product.id = p.id WHERE c.user.id = :userId")
                    .setParameter("userId", userId)
                    .list();
        } catch (Exception e) {
            throw new DaoException("Error finding products from cart: " + e.getMessage());
        }
    }

    @Override
    public void setImage(Long id, String filePath) {
        try (Session session = sessionFactory.openSession()) {
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
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Product p WHERE p.categoryId = :categoryId", Product.class)
                    .setParameter("categoryId", categoryId)
                    .list();
        } catch (Exception e) {
            throw new DaoException("Error finding products by category ID: " + e.getMessage());
        }
    }

    @Override
    public List<Product> findByQuery(String query) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Product p WHERE p.name LIKE :query", Product.class)
                    .setParameter("query", "%" + query + "%")
                    .list();
        } catch (Exception e) {
            throw new DaoException("Error finding products by query: " + e.getMessage());
        }
    }

    @Override
    public Product findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
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
