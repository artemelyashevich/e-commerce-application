package org.elyashevich.ecommerce.command;

import lombok.Getter;
import org.elyashevich.ecommerce.command.cart.CartAddProductCommand;
import org.elyashevich.ecommerce.command.cart.CartDeleteProductCommand;
import org.elyashevich.ecommerce.command.cart.CartViewCommand;
import org.elyashevich.ecommerce.command.category.CategoriesViewCommand;
import org.elyashevich.ecommerce.command.category.CategoryCreateCommand;
import org.elyashevich.ecommerce.command.category.CategoryDeleteCommand;
import org.elyashevich.ecommerce.command.login.LoginActionCommand;
import org.elyashevich.ecommerce.command.login.LoginViewCommand;
import org.elyashevich.ecommerce.command.logout.LogoutActionCommand;
import org.elyashevich.ecommerce.command.order.OrderCreateActionCommand;
import org.elyashevich.ecommerce.command.order.OrderDeleteActionCommand;
import org.elyashevich.ecommerce.command.order.OrdersViewCommand;
import org.elyashevich.ecommerce.command.product.ProductAdminViewCommand;
import org.elyashevich.ecommerce.command.product.ProductCreateActionCommand;
import org.elyashevich.ecommerce.command.product.ProductCreateViewCommand;
import org.elyashevich.ecommerce.command.product.ProductDeleteActionCommand;
import org.elyashevich.ecommerce.command.product.ProductFilterActionCommand;
import org.elyashevich.ecommerce.command.product.ProductSearchActionCommand;
import org.elyashevich.ecommerce.command.product.ProductViewCommand;
import org.elyashevich.ecommerce.command.product.ProductViewPaginatedCommand;
import org.elyashevich.ecommerce.command.product.ProductsAdminViewCommand;
import org.elyashevich.ecommerce.command.product.ProductsViewCommand;
import org.elyashevich.ecommerce.command.register.RegisterActionCommand;
import org.elyashevich.ecommerce.command.register.RegisterViewCommand;
import org.elyashevich.ecommerce.command.upload.ProductUploadImageCommand;
import org.elyashevich.ecommerce.command.upload.UserUploadImageCommand;
import org.elyashevich.ecommerce.command.users.UsersViewCommand;

@Getter
public enum CommandType {

    // category
    CATEGORIES(CategoriesViewCommand.getInstance()),
    CREATE_CATEGORY(CategoryCreateCommand.getInstance()),
    DELETE_CATEGORY(CategoryDeleteCommand.getInstance()),
    ADMIN_CATEGORIES(CategoriesViewCommand.getInstance()),
    // product
    PRODUCT(ProductViewCommand.getInstance()),
    PRODUCTS(ProductsViewCommand.getInstance()),
    PRODUCTS_PAGINATED(ProductViewPaginatedCommand.getInstance()),
    ADMIN_PRODUCT(ProductAdminViewCommand.getInstance()),
    ADMIN_PRODUCTS(ProductsAdminViewCommand.getInstance()),
    ADMIN_CREATE_PRODUCT(ProductCreateViewCommand.getInstance()),
    CREATE_PRODUCT_ACTION(ProductCreateActionCommand.getInstance()),
    FILTER_PRODUCT_ACTION(ProductFilterActionCommand.getInstance()),
    SEARCH_PRODUCT_ACTION(ProductSearchActionCommand.getInstance()),
    DELETE_PRODUCT_ACTION(ProductDeleteActionCommand.getInstance()),
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
    ADMIN_USERS(UsersViewCommand.getInstance()),
    // order
    ADMIN_ORDERS(OrdersViewCommand.getInstance()),
    DELETE_ORDER_ACTION(OrderDeleteActionCommand.getInstance()),
    CREATE_ORDER_ACTION(OrderCreateActionCommand.getInstance());

    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }
}
