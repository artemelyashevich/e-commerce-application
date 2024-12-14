package org.elyashevich.ecommerceapplication.dao;

import org.elyashevich.ecommerceapplication.entity.Product;

import java.util.List;

public interface ProductDao extends BaseDao<Product> {

    List<Product> findFromCart(final Long userId);

    void setImage(final Long id, final String filePath);

    List<Product> findByCategoryId(final Long categoryId);

    List<Product> findByQuery(final String query);

    Product findById(final Long id);
}
