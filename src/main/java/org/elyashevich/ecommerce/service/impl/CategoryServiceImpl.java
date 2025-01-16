package org.elyashevich.ecommerce.service.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elyashevich.ecommerce.dao.CategoryDao;
import org.elyashevich.ecommerce.dao.impl.CategoryDaoImpl;
import org.elyashevich.ecommerce.entity.Category;
import org.elyashevich.ecommerce.exception.ResourceNotFoundException;
import org.elyashevich.ecommerce.service.CategoryService;

import java.util.List;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryServiceImpl implements CategoryService {

    @Getter
    private static final CategoryServiceImpl instance = new CategoryServiceImpl();
    private static final String ERROR_TEMPLATE = "Category with id '%s' was not found.";

    private final CategoryDao categoryDao = CategoryDaoImpl.getInstance();

    @Override
    public void create(final Category category) {
        log.info("Attempting to create new category: {}", category);

        this.categoryDao.create(category);

        log.info("Category has been created: {}", category);
    }

    @Override
    public List<Category> findAll() {
        log.info("Attempting to find all categories");

        var categories = this.categoryDao.findAll();

        log.info("All categories has been found");
        return categories;
    }

    @Override
    public void update(final Long id, final Category category) {
        log.info("Attempting to update category with id: {}", id);

        this.categoryDao.update(id, category);

        log.info("Category with id: '{}' has been updated", id);
    }

    @Override
    public void delete(final Long id) {
        log.info("Attempting to delete category with id: {}", id);

        this.categoryDao.delete(id);

        log.info("Category with id: '{}' has been deleted", id);
    }

    @Override
    public Category findById(final Long id) {
        log.info("Attempting to find category with id: {}", id);

        var category = this.categoryDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ERROR_TEMPLATE.formatted(id)));

        log.info("Category with id: '{}' has been found", id);
        return category;
    }
}
