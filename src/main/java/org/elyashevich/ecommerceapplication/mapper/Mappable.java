package org.elyashevich.ecommerceapplication.mapper;

import org.elyashevich.ecommerceapplication.entity.AbstractEntity;

import java.util.List;

public interface Mappable <E extends AbstractEntity, T>{

    T toDto(final E e);

    List<T> toDto(final List<E> e);

    E toEntity(final T t);
}
