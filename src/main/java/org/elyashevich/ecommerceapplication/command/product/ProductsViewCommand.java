package org.elyashevich.ecommerceapplication.command.product;

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

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductsViewCommand implements Command {

    @Getter
    private static final ProductsViewCommand instance = new ProductsViewCommand();

    private final ProductService productService = ProductServiceImpl.getInstance();
    private final ProductMapper productMapper = ProductMapperImpl.getInstance();

    @Override
    public Router execute(final HttpServletRequest request) {
        var router = new Router();
        var products = this.productService.findAll();
        request.setAttribute("products", this.productMapper.toDto(products));
        router.setType(RouterType.FORWARD);
        router.setPath("products");
        return router;
    }
}
