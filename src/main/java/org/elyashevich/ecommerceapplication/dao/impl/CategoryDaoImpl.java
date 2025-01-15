package org.elyashevich.ecommerceapplication.dao.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerceapplication.dao.CategoryDao;
import org.elyashevich.ecommerceapplication.entity.Category;
import org.elyashevich.ecommerceapplication.exception.DaoException;
import org.elyashevich.ecommerceapplication.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryDaoImpl implements CategoryDao {

    @Getter
    private static final CategoryDaoImpl instance = new CategoryDaoImpl();

    @Override
    public void create(final Category category) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(category);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException("Error occurred while saving category: " + e.getMessage());
        }
    }

    @Override
    public List<Category> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Category", Category.class).list();
        } catch (Exception e) {
            throw new DaoException("Error occurred while retrieving categories: " + e.getMessage());
        }
    }

    @Override
    public void update(final Long id, final Category category) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Category existingCategory = session.get(Category.class, id);
            if (existingCategory != null) {
                existingCategory.setName(category.getName());
                session.update(existingCategory);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException("Error occurred while updating category: " + e.getMessage());
        }
    }

    @Override
    public void delete(final Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Category category = session.get(Category.class, id);
            if (category != null) {
                session.delete(category);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException("Error occurred while deleting category: " + e.getMessage());
        }
    }

    @Override
    public Optional<Category> findById(final Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Category category = session.get(Category.class, id);
            return Optional.ofNullable(category);
        } catch (Exception e) {
            throw new DaoException("Error occurred while finding category by ID: " + e.getMessage());
        }
    }
}
