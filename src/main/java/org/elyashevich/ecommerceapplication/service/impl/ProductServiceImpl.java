package org.elyashevich.ecommerceapplication.service.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerceapplication.dao.ProductDao;
import org.elyashevich.ecommerceapplication.dao.impl.ProductDaoImpl;
import org.elyashevich.ecommerceapplication.entity.Product;
import org.elyashevich.ecommerceapplication.service.ProductService;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductServiceImpl implements ProductService {

    @Getter
    private static final ProductServiceImpl instance = new ProductServiceImpl();

    private final ProductDao productDao = ProductDaoImpl.getInstance();

    @Override
    public void create(final Product product) {
        this.productDao.create(product);
    }

    @Override
    public List<Product> findAll() {
        return this.productDao.findAll();
    }

    @Override
    public void update(final Long id, final Product product) {
        this.productDao.update(id ,product);
    }

    @Override
    public void delete(final Long id) {
        this.productDao.delete(id);
    }

    @Override
    public List<Product> findFromCartByUser(final Long userId) {
        return this.productDao.findFromCart(userId);
    }

    @Override
    public List<Product> findByCategoryId(final Long categoryId) {
        return this.productDao.findByCategoryId(categoryId);
    }

    @Override
    public List<Product> findByQuery(final String query) {
        return this.productDao.findByQuery(query);
    }

    @Override
    public void setImage(final Long id, final String filePath) {
        this.productDao.setImage(id, filePath);
    }
}
