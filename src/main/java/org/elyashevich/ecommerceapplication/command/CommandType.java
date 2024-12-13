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
import org.elyashevich.ecommerceapplication.command.order.OrderCreateActionCommand;
import org.elyashevich.ecommerceapplication.command.order.OrdersViewCommand;
import org.elyashevich.ecommerceapplication.command.product.*;
import org.elyashevich.ecommerceapplication.command.register.RegisterActionCommand;
import org.elyashevich.ecommerceapplication.command.register.RegisterViewCommand;
import org.elyashevich.ecommerceapplication.command.upload.ProductUploadImageCommand;
import org.elyashevich.ecommerceapplication.command.upload.UserUploadImageCommand;
import org.elyashevich.ecommerceapplication.command.users.UsersViewCommand;

@Getter
public enum CommandType {

    // category
    CREATE_CATEGORY(CategoryCreateCommand.getInstance()),
    DELETE_CATEGORY(CategoryDeleteCommand.getInstance()),
    CATEGORIES(CategoriesViewCommand.getInstance()),
    // product
    PRODUCTS(ProductsViewCommand.getInstance()),
    ADMIN_PRODUCT(ProductCreateViewCommand.getInstance()),
    CREATE_PRODUCT(ProductCreateViewCommand.getInstance()),
    CREATE_PRODUCT_ACTION(ProductCreateActionCommand.getInstance()),
    FILTER_PRODUCT_ACTION(ProductFilterActionCommand.getInstance()),
    SEARCH_PRODUCT_ACTION(ProductSearchActionCommand.getInstance()),
    // auth
    LOGIN(LoginViewCommand.getInstance()),
    LOGIN_ACTION(LoginActionCommand.getInstance()),
    REGISTER(RegisterViewCommand.getInstance()),
    REGISTER_ACTION(RegisterActionCommand.getInstance()),
    LOGOUT(LogoutActionCommand.getInstance()),
    // cart
    CART(CartViewCommand.getInstance()),
    ADD_PRODUCT_CART(CartAddProductCommand.getInstance()),
    DELETE_PRODUCT_CART(CartDeleteProductCommand.getInstance()),
    // upload
    UPLOAD_USER_IMAGE(UserUploadImageCommand.getInstance()),
    UPLOAD_PRODUCT_IMAGE(ProductUploadImageCommand.getInstance()),
    // user
    USERS(UsersViewCommand.getInstance()),
    // order
    ORDERS(OrdersViewCommand.getInstance()),
    CREATE_ORDER_ACTION(OrderCreateActionCommand.getInstance());

    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }
}
