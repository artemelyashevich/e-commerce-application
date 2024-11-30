package org.elyashevich.ecommerceapplication.service;

import org.elyashevich.ecommerceapplication.entity.Category;

public interface CategoryService extends BaseService<Category> {

    Category findById(final Long id);
}
