package org.elyashevich.ecommerce.service;

import org.elyashevich.ecommerce.dto.PaginatedProductDto;
import org.elyashevich.ecommerce.entity.Product;

import java.util.List;

public interface ProductService extends BaseService<Product> {

    List<Product> findFromCartByUser(final Long userId);

    List<Product> findByCategoryId(final Long categoryId);

    List<Product> findByQuery(final String query);

    Product findById(final Long id);

    PaginatedProductDto findPaginated(final Integer pageNumber);
}
