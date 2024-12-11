package org.elyashevich.ecommerceapplication.service;

import org.elyashevich.ecommerceapplication.entity.Product;

import java.util.List;

public interface ProductService extends BaseService<Product> {

    List<Product> findFromCartByUser(final Long userId);

    List<Product> findByCategoryId(final Long categoryId);

    void setImage(final Long id, final String filePath);
}
