package org.elyashevich.ecommerceapplication.service.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.elyashevich.ecommerceapplication.dao.CategoryDao;
import org.elyashevich.ecommerceapplication.dao.impl.CategoryDaoImpl;
import org.elyashevich.ecommerceapplication.entity.Category;
import org.elyashevich.ecommerceapplication.exception.ResourceNotFoundException;
import org.elyashevich.ecommerceapplication.service.CategoryService;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryServiceImpl implements CategoryService {

    @Getter
    private static final CategoryServiceImpl instance = new CategoryServiceImpl();
    private static final String ERROR_TEMPLATE = "Category with id '%s' was not found.";

    private final CategoryDao categoryDao = CategoryDaoImpl.getInstance();

    @Override
    public void create(final Category category) {
        this.categoryDao.create(category);
    }

    @Override
    public List<Category> findAll() {
        return this.categoryDao.findAll();
    }

    @Override
    public void update(final Long id, final Category category) {
        this.categoryDao.update(id, category);
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
