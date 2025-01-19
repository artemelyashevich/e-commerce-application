package org.elyashevich.ecommerce.service.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elyashevich.ecommerce.dao.ProductDao;
import org.elyashevich.ecommerce.dao.impl.ProductDaoImpl;
import org.elyashevich.ecommerce.dto.PaginatedProductDto;
import org.elyashevich.ecommerce.entity.Product;
import org.elyashevich.ecommerce.service.ProductService;

import java.util.List;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductServiceImpl implements ProductService {

    @Getter
    private static final ProductServiceImpl instance = new ProductServiceImpl();

    private final ProductDao productDao = ProductDaoImpl.getInstance();

    @Override
    public void create(final Product product) {
        log.info("Attempting to create new product: {}", product);

        this.productDao.create(product);

        log.info("Product has been created: {}", product);
    }

    @Override
    public List<Product> findAll() {
        log.info("Attempting to find all products");

        var products = this.productDao.findAll();

        log.info("All products have been found");
        return products;
    }

    @Override
    public void update(final Long id, final Product product) {
        log.info("Attempting to update product with id: {}", id);

        this.productDao.update(id ,product);

        log.info("Order with id: '{}' has been updated", id);
    }

    @Override
    public void delete(final Long id) {
        log.info("Attempting to delete product with id: {}", id);

        this.productDao.delete(id);

        log.info("Product with id: '{}' has been deleted", id);
    }

    @Override
    public List<Product> findFromCartByUser(final Long userId) {
        log.info("Attempting to find products by user id: {}", userId);

        var products = this.productDao.findFromCart(userId);

        log.info("Products have been found by user id: {}", userId);
        return products;
    }

    @Override
    public List<Product> findByCategoryId(final Long categoryId) {
        log.info("Attempting to find products by category id: {}", categoryId);

        var products = this.productDao.findByCategoryId(categoryId);

        log.info("Products have been found by category id: {}", categoryId);
        return products;
    }

    @Override
    public List<Product> findByQuery(final String query) {
        log.info("Attempting to find products by query: {}", query);

        var products =  this.productDao.findByQuery(query);

        log.info("Products have been found by query: {}", query);
        return products;
    }

    @Override
    public Product findById(final Long id) {
        log.info("Attempting to find product with id: {}", id);

        var product = this.productDao.findById(id);

        log.info("Product with id: '{}' has been found", id);
        return product;
    }

    @Override
    public PaginatedProductDto findPaginated(Integer pageNumber) {
        return this.productDao.findAllPaginated(pageNumber);
    }
}
