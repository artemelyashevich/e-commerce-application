package org.elyashevich.ecommerceapplication.command;

import lombok.Getter;
import org.elyashevich.ecommerceapplication.command.category.CategoriesViewCommand;
import org.elyashevich.ecommerceapplication.command.category.CreateCategoryCommand;
import org.elyashevich.ecommerceapplication.command.category.DeleteCategoryCommand;
import org.elyashevich.ecommerceapplication.command.login.LoginActionCommand;
import org.elyashevich.ecommerceapplication.command.login.LoginViewCommand;
import org.elyashevich.ecommerceapplication.command.logout.LogoutActionCommand;
import org.elyashevich.ecommerceapplication.command.product.ProductCreateViewCommand;
import org.elyashevich.ecommerceapplication.command.product.CreateProductCommand;
import org.elyashevich.ecommerceapplication.command.product.ProductsViewCommand;
import org.elyashevich.ecommerceapplication.command.register.RegisterActionCommand;
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
    LOGIN_ACTION(LoginActionCommand.getInstance()),
    REGISTER(RegisterViewCommand.getInstance()),
    REGISTER_ACTION(RegisterActionCommand.getInstance()),
    LOGOUT(LogoutActionCommand.getInstance());

    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }
}
