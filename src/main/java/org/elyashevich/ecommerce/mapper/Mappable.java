package org.elyashevich.ecommerce.mapper;

import org.elyashevich.ecommerce.entity.AbstractEntity;

import java.util.List;

public interface Mappable <E extends AbstractEntity, T>{

    T toDto(final E e);

    List<T> toDto(final List<E> e);

    E toEntity(final T t);
}
