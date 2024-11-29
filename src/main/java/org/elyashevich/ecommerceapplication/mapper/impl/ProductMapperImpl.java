package org.elyashevich.ecommerceapplication.mapper.impl;

import org.elyashevich.ecommerceapplication.dto.ProductDto;
import org.elyashevich.ecommerceapplication.entity.Product;
import org.elyashevich.ecommerceapplication.mapper.ProductMapper;

import java.util.List;

public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductDto toDto(final Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCategoryId()
        );
    }

    @Override
    public List<ProductDto> toDto(final List<Product> entities) {
        return entities.stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public Product toEntity(final ProductDto productDto) {
        return Product.builder()
                .name(productDto.name())
                .description(productDto.description())
                .price(productDto.price())
                .categoryId(productDto.categoryId())
                .build();
    }
}
