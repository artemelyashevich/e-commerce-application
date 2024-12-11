package org.elyashevich.ecommerceapplication.mapper.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerceapplication.dto.ProductDto;
import org.elyashevich.ecommerceapplication.entity.Product;
import org.elyashevich.ecommerceapplication.mapper.ProductMapper;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductMapperImpl implements ProductMapper {

    @Getter
    private static final ProductMapperImpl instance = new ProductMapperImpl();

    @Override
    public ProductDto toDto(final Product product) {
        return
                ProductDto.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .description(product.getDescription())
                        .price(product.getPrice())
                        .categoryId(product.getCategoryId())
                        .image(product.getImage())
                        .build();
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
                .name(productDto.getName())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .image(productDto.getImage())
                .categoryId(productDto.getCategoryId())
                .build();
    }
}
