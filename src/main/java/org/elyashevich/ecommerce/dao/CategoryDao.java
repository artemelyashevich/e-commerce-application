package org.elyashevich.ecommerce.dao;

import org.elyashevich.ecommerce.entity.Category;

import java.util.Optional;

public interface CategoryDao extends BaseDao<Category> {
    Optional<Category> findById(final Long id);
}
