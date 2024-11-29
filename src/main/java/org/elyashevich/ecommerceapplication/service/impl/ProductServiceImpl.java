package org.elyashevich.ecommerceapplication.service.impl;

import org.elyashevich.ecommerceapplication.dao.BaseDao;
import org.elyashevich.ecommerceapplication.dao.impl.ProductDaoImpl;
import org.elyashevich.ecommerceapplication.entity.Product;
import org.elyashevich.ecommerceapplication.service.BaseService;
import org.elyashevich.ecommerceapplication.service.ProductService;

import java.util.List;

public class ProductServiceImpl extends BaseService<Product> implements ProductService {

    private final BaseDao<Product> productDao;

    public ProductServiceImpl() {
        this.productDao = ProductDaoImpl.getInstance();
    }

    @Override
    public void create(Product product) {

    }

    @Override
    public List<Product> findAll() {
        return null;
    }

    @Override
    public Product update(Long id, Product product) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
