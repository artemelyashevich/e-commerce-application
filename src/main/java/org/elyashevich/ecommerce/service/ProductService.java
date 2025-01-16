package org.elyashevich.ecommerce.service;

import org.elyashevich.ecommerce.entity.Product;

import java.util.List;

public interface ProductService extends BaseService<Product> {

    List<Product> findFromCartByUser(final Long userId);

    List<Product> findByCategoryId(final Long categoryId);

    List<Product> findByQuery(final String query);

    void setImage(final Long id, final String filePath);

    Product findById(final Long id);
}
