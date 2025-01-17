package org.elyashevich.ecommerce.dao;

import lombok.extern.slf4j.Slf4j;
import org.elyashevich.ecommerce.config.HibernateSessionFactorySingleton;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.elyashevich.ecommerce.exception.DaoException;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Slf4j
public abstract class AbstractDao<T, K extends Serializable> implements GenericDao<T, K> {

    private final Class<T> entityType;
    protected final SessionFactory sessionFactory;

    protected AbstractDao(Class<T> entityType) {
        this.entityType = entityType;
        this.sessionFactory = HibernateSessionFactorySingleton.getInstance();
    }

    @Override
    public T findById(K id) {
        try (var session = sessionFactory.openSession()) {
            return session.get(entityType, id);
        } catch (Exception e) {
            throw new DaoException("Session operation failed: " + e.getMessage(), e);
        }
    }

    @Override
    public T create(T entity) {
        return executeInsideTransaction(session -> {
            session.save(entity);
            return entity;
        });
    }

    @Override
    public List<T> findAll() {
        try (var session = sessionFactory.openSession()) {
            return session.createQuery("FROM " + entityType.getSimpleName(), entityType).list();
        } catch (Exception e) {
            throw new DaoException("Session operation failed: " + e.getMessage(), e);
        }
    }

    @Override
    public void update(K id, T entity) {
        executeInsideTransaction(session -> {
            setId(entity, id);
            session.update(entity);
            return null;
        });
    }

    @Override
    public void delete(K id) {
        executeInsideTransaction(session -> {
            T entity = session.load(entityType, id);
            session.delete(entity);
            return null;
        });
    }

    public List<T> findByAttributes(Map<String, Object> attributes) {
        try (var session = sessionFactory.openSession()) {
            var criteriaBuilder = session.getCriteriaBuilder();
            var criteria = criteriaBuilder.createQuery(entityType);
            var root = criteria.from(entityType);

            attributes.forEach(
                    (key, value) ->
                            criteria.where(criteriaBuilder.equal(root.get(key), value))
            );

            var query = session.createQuery(criteria);
            return query.getResultList();
        } catch (Exception e) {
            throw new DaoException("Error executing dynamic query: " + e.getMessage(), e);
        }
    }

    protected abstract void setId(T entity, K id);

    private <R> R executeInsideTransaction(Function<Session, R> function) {
        Transaction transaction = null;
        try (var session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            var result = function.apply(session);
            transaction.commit();

            return result;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException("Transaction failed: " + e.getMessage(), e);
        }
    }
}

