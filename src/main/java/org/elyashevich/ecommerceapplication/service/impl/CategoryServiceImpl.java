package org.elyashevich.ecommerceapplication.service.impl;

import lombok.RequiredArgsConstructor;
import org.elyashevich.ecommerceapplication.dao.CategoryDao;
import org.elyashevich.ecommerceapplication.entity.Category;
import org.elyashevich.ecommerceapplication.exception.ResourceNotFoundException;
import org.elyashevich.ecommerceapplication.service.CategoryService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final static String ERROR_TEMPLATE = "Category with id '%s' was not found.";

    private final CategoryDao categoryDao;

    @Override
    public void create(final Category category) {
        this.categoryDao.create(category);
    }

    @Override
    public List<Category> findAll() {
        return this.categoryDao.findAll();
    }

    @Override
    public Category update(final Long id, final Category category) {
        return this.categoryDao.update(id, category);
    }

    @Override
    public void delete(final Long id) {
        this.categoryDao.delete(id);
    }

    @Override
    public Category findById(final Long id) {
        return this.categoryDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ERROR_TEMPLATE.formatted(id)));
    }
}
