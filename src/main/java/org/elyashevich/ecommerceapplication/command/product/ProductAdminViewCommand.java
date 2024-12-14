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
import org.elyashevich.ecommerceapplication.util.QueryParameterUtil;

import java.io.IOException;
import java.util.Enumeration;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductAdminViewCommand implements Command {

    @Getter
    private static final ProductAdminViewCommand instance = new ProductAdminViewCommand();

    private final ProductService productService = ProductServiceImpl.getInstance();
    private final ProductMapper productMapper = ProductMapperImpl.getInstance();

    @Override
    public Router execute(final HttpServletRequest request) throws ServletException, IOException {
        var router = new Router();

        var id = QueryParameterUtil.getParameters(request).get("id")[0];
        var product = this.productService.findById(Long.valueOf(id));

        request.setAttribute("product", this.productMapper.toDto(product));
        router.setPath("admin-product");
        router.setType(RouterType.FORWARD);
        return router;
    }
}
