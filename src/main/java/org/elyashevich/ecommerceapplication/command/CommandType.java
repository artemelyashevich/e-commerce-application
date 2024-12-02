package org.elyashevich.ecommerceapplication.command;

import lombok.Getter;
import org.elyashevich.ecommerceapplication.command.category.CategoriesViewCommand;
import org.elyashevich.ecommerceapplication.command.category.CreateCategoryCommand;
import org.elyashevich.ecommerceapplication.command.category.DeleteCategoryCommand;
import org.elyashevich.ecommerceapplication.command.login.LoginViewCommand;
import org.elyashevich.ecommerceapplication.command.product.ProductCreateViewCommand;
import org.elyashevich.ecommerceapplication.command.product.CreateProductCommand;
import org.elyashevich.ecommerceapplication.command.product.ProductsViewCommand;
import org.elyashevich.ecommerceapplication.command.register.RegisterViewCommand;

@Getter
public enum CommandType {

    CREATE_CATEGORY(CreateCategoryCommand.getInstance()),
    DELETE_CATEGORY(DeleteCategoryCommand.getInstance()),
    CATEGORIES(CategoriesViewCommand.getInstance()),
    PRODUCTS(ProductsViewCommand.getInstance()),
    ADMIN_PRODUCT(ProductCreateViewCommand.getInstance()),
    CREATE_PRODUCT(CreateProductCommand.getInstance()),
    LOGIN(LoginViewCommand.getInstance()),
    REGISTER(RegisterViewCommand.getInstance());
    
    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }
}
