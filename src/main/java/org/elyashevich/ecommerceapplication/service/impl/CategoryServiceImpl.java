package org.elyashevich.ecommerceapplication.service.impl;

import lombok.RequiredArgsConstructor;
import org.elyashevich.ecommerceapplication.dao.CategoryDao;
import org.elyashevich.ecommerceapplication.entity.Category;
import org.elyashevich.ecommerceapplication.service.CategoryService;

import java.util.List;

@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

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
}
