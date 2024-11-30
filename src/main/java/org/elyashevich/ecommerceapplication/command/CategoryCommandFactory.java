package org.elyashevich.ecommerceapplication.command;

import lombok.RequiredArgsConstructor;
import org.elyashevich.ecommerceapplication.command.category.CreateCategoryCommand;
import org.elyashevich.ecommerceapplication.command.category.DeleteCategoryCommand;
import org.elyashevich.ecommerceapplication.mapper.CategoryMapper;
import org.elyashevich.ecommerceapplication.service.CategoryService;

@RequiredArgsConstructor
public class CategoryCommandFactory {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    public Command getCommand(final String method) {
        return switch (method) {
            case "post" -> new CreateCategoryCommand(categoryService, categoryMapper);
            case "delete" -> new DeleteCategoryCommand(categoryService);
            default -> throw new IllegalStateException("Invalid command.");
        };
    }
}
