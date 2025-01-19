package org.elyashevich.ecommerce.dao;

import org.elyashevich.ecommerce.dto.PaginatedProductDto;
import org.elyashevich.ecommerce.entity.Product;
import org.elyashevich.ecommerce.exception.DaoException;

import java.util.List;

public interface ProductDao extends GenericDao<Product, Long> {

    List<Product> findFromCart(final Long userId);

    void setImage(final Long id, final String filePath);

    List<Product> findByCategoryId(final Long categoryId);

    List<Product> findByQuery(final String query);

    PaginatedProductDto findAllPaginated(int pageNumber) throws DaoException;
}
