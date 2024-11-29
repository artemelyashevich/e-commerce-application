package org.elyashevich.ecommerceapplication.dao;

import org.elyashevich.ecommerceapplication.entity.AbstractEntity;

import java.util.List;

public abstract class BaseDao<T extends AbstractEntity> {

    public abstract void create(final T t);

    public abstract List<T> findAll();

    public abstract T update(final Long id, final T t);

    public abstract void delete(final Long id);
}
