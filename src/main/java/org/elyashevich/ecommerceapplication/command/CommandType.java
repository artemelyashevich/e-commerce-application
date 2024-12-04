package org.elyashevich.ecommerceapplication.command;

import lombok.Getter;
import org.elyashevich.ecommerceapplication.command.cart.CartAddProductCommand;
import org.elyashevich.ecommerceapplication.command.cart.CartDeleteProductCommand;
import org.elyashevich.ecommerceapplication.command.cart.CartViewCommand;
import org.elyashevich.ecommerceapplication.command.category.CategoriesViewCommand;
import org.elyashevich.ecommerceapplication.command.category.CategoryCreateCommand;
import org.elyashevich.ecommerceapplication.command.category.CategoryDeleteCommand;
import org.elyashevich.ecommerceapplication.command.login.LoginActionCommand;
import org.elyashevich.ecommerceapplication.command.login.LoginViewCommand;
import org.elyashevich.ecommerceapplication.command.logout.LogoutActionCommand;
import org.elyashevich.ecommerceapplication.command.product.ProductCreateViewCommand;
import org.elyashevich.ecommerceapplication.command.product.ProductCreateCommand;
import org.elyashevich.ecommerceapplication.command.product.ProductsViewCommand;
import org.elyashevich.ecommerceapplication.command.register.RegisterActionCommand;
import org.elyashevich.ecommerceapplication.command.register.RegisterViewCommand;

@Getter
public enum CommandType {

    CREATE_CATEGORY(CategoryCreateCommand.getInstance()),
    DELETE_CATEGORY(CategoryDeleteCommand.getInstance()),
    CATEGORIES(CategoriesViewCommand.getInstance()),
    PRODUCTS(ProductsViewCommand.getInstance()),
    ADMIN_PRODUCT(ProductCreateViewCommand.getInstance()),
    CREATE_PRODUCT(ProductCreateCommand.getInstance()),
    LOGIN(LoginViewCommand.getInstance()),
    LOGIN_ACTION(LoginActionCommand.getInstance()),
    REGISTER(RegisterViewCommand.getInstance()),
    REGISTER_ACTION(RegisterActionCommand.getInstance()),
    LOGOUT(LogoutActionCommand.getInstance()),
    CART(CartViewCommand.getInstance()),
    ADD_PRODUCT_CART(CartAddProductCommand.getInstance()),
    DELETE_PRODUCT_CART(CartDeleteProductCommand.getInstance());

    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }
}
