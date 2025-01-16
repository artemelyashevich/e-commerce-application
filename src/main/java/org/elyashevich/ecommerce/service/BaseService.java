package org.elyashevich.ecommerce.service;

import org.elyashevich.ecommerce.entity.AbstractEntity;

import java.util.List;

public interface BaseService<T extends AbstractEntity> {

    void create(final T t);

    List<T> findAll();

    void update(final Long id, final T t);

    void delete(final Long id);
}
