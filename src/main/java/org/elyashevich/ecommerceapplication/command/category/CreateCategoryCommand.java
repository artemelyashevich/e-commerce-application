package org.elyashevich.ecommerceapplication.command.category;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.elyashevich.ecommerceapplication.command.Command;
import org.elyashevich.ecommerceapplication.dto.CategoryDto;
import org.elyashevich.ecommerceapplication.mapper.CategoryMapper;
import org.elyashevich.ecommerceapplication.service.CategoryService;

@RequiredArgsConstructor
public class CreateCategoryCommand implements Command {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @Override
    public void execute(HttpServletRequest request) {
        var categoryDto = CategoryDto.builder()
                .name(request.getParameter("name"))
                .build();
        this.categoryService.create(this.categoryMapper.toEntity(categoryDto));
    }
}
