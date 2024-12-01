package org.elyashevich.ecommerceapplication.command;

import lombok.Getter;
import org.elyashevich.ecommerceapplication.command.category.CategoriesCommand;
import org.elyashevich.ecommerceapplication.command.category.CreateCategoryCommand;
import org.elyashevich.ecommerceapplication.command.category.DeleteCategoryCommand;
import org.elyashevich.ecommerceapplication.command.product.ProductsCommand;

@Getter
public enum CommandType {

    CREATE_CATEGORY(CreateCategoryCommand.getInstance()),
    DELETE_CATEGORY(DeleteCategoryCommand.getInstance()),
    CATEGORIES(CategoriesCommand.getInstance()),
    PRODUCTS(ProductsCommand.getInstance());

    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }
}
