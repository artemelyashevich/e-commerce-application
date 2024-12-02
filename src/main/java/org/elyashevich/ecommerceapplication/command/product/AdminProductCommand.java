package org.elyashevich.ecommerceapplication.command.product;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerceapplication.command.Command;
import org.elyashevich.ecommerceapplication.command.Router;
import org.elyashevich.ecommerceapplication.service.ProductService;
import org.elyashevich.ecommerceapplication.service.impl.ProductServiceImpl;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AdminProductCommand implements Command {

    @Getter
    private static final AdminProductCommand instance = new AdminProductCommand();

    private final ProductService productService = ProductServiceImpl.getInstance();

    @Override
    public Router execute(final HttpServletRequest request) {
        return null;
    }
}
