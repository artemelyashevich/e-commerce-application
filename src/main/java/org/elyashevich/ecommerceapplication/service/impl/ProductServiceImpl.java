package org.elyashevich.ecommerceapplication.service.impl;

import lombok.RequiredArgsConstructor;
import org.elyashevich.ecommerceapplication.dao.ProductDao;
import org.elyashevich.ecommerceapplication.entity.Product;
import org.elyashevich.ecommerceapplication.service.ProductService;

import java.util.List;

@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;

    @Override
    public void create(final Product product) {
        this.productDao.create(product);
    }

    @Override
    public List<Product> findAll() {
        return this.productDao.findAll();
    }

    @Override
    public Product update(final Long id, final Product product) {
        return this.productDao.update(id ,product);
    }

    @Override
    public void delete(final Long id) {
        this.productDao.delete(id);
    }
}
