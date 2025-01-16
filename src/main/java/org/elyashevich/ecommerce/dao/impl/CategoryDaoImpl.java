package org.elyashevich.ecommerce.dao.impl;

import lombok.Getter;
import org.elyashevich.ecommerce.dao.AbstractDao;
import org.elyashevich.ecommerce.entity.Category;

public class CategoryDaoImpl extends AbstractDao<Category, Long> {

    @Getter
    private static final CategoryDaoImpl instance = new CategoryDaoImpl();

    private CategoryDaoImpl() {
        super(Category.class);
    }

    @Override
    protected void setId(Category entity, Long id) {
        entity.setId(id);
    }
}
