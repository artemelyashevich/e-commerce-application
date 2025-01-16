package org.elyashevich.ecommerce.service;

import org.elyashevich.ecommerce.entity.Category;

public interface CategoryService extends BaseService<Category> {

    Category findById(final Long id);
}
