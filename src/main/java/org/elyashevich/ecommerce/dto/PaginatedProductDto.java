package org.elyashevich.ecommerce.dto;

import org.elyashevich.ecommerce.entity.Product;

import java.util.List;

public record PaginatedProductDto(
        int lastPage,
        List<Product> products
) {
}
