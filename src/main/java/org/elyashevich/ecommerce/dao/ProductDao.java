package org.elyashevich.ecommerce.dao;

import org.elyashevich.ecommerce.entity.Product;

import java.util.List;

public interface ProductDao extends GenericDao<Product, Long> {

    List<Product> findFromCart(final Long userId);

    void setImage(final Long id, final String filePath);

    List<Product> findByCategoryId(final Long categoryId);

    // Spring data
    List<Product> findByQuery(final String query);
}
