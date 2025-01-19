package org.elyashevich.ecommerce.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T, K extends Serializable> {
    T findById(K id);

    T create(T entity);

    List<T> findAll();

    void update(K id, T entity);

    void delete(K id);
}
