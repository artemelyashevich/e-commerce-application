package org.elyashevich.ecommerceapplication.dao;

import org.elyashevich.ecommerceapplication.entity.Category;

import java.util.Optional;

public interface CategoryDao extends BaseDao<Category> {
    Optional<Category> findById(final Long id);
}
