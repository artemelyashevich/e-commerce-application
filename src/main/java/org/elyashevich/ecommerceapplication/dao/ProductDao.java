package org.elyashevich.ecommerceapplication.dao;

import org.elyashevich.ecommerceapplication.entity.Product;

import java.util.List;

public interface ProductDao extends BaseDao<Product> {

    List<Product> findFromCart(final Long userId);
}
