package org.elyashevich.ecommerceapplication.dao;

import org.elyashevich.ecommerceapplication.entity.AbstractEntity;

import java.util.List;

public interface BaseDao<T extends AbstractEntity> {

    void create(final T t);

    List<T> findAll();

    T update(final Long id, final T t);

    void delete(final Long id);
}
