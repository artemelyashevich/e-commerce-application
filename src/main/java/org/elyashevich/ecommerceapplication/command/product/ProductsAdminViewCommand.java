package org.elyashevich.ecommerceapplication.command.product;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerceapplication.command.Command;
import org.elyashevich.ecommerceapplication.command.Router;
import org.elyashevich.ecommerceapplication.command.RouterType;
import org.elyashevich.ecommerceapplication.mapper.ProductMapper;
import org.elyashevich.ecommerceapplication.mapper.impl.ProductMapperImpl;
import org.elyashevich.ecommerceapplication.service.ProductService;
import org.elyashevich.ecommerceapplication.service.impl.ProductServiceImpl;

import java.io.IOException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductsAdminViewCommand implements Command {

    @Getter
    private static final ProductsAdminViewCommand instance = new ProductsAdminViewCommand();

    private final ProductService productService = ProductServiceImpl.getInstance();
    private final ProductMapper productMapper = ProductMapperImpl.getInstance();

    @Override
    public Router execute(final HttpServletRequest request) throws ServletException, IOException {
        var router = new Router();
        var products = this.productService.findAll();
        request.setAttribute("products", this.productMapper.toDto(products));
        router.setType(RouterType.FORWARD);
        router.setPath("admin-products");
        return router;
    }
}
