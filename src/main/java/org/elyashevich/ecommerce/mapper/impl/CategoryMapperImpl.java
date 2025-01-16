package org.elyashevich.ecommerce.mapper.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerce.dto.CategoryDto;
import org.elyashevich.ecommerce.entity.Category;
import org.elyashevich.ecommerce.mapper.CategoryMapper;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryMapperImpl implements CategoryMapper {

    @Getter
    private static final CategoryMapperImpl instance = new CategoryMapperImpl();

    @Override
    public CategoryDto toDto(final Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    @Override
    public List<CategoryDto> toDto(final List<Category> entities) {
        return entities.stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public Category toEntity(final CategoryDto categoryDto) {
        return Category.builder()
                .id(categoryDto.getId())
                .name(categoryDto.getName())
                .build();
    }
}
