package org.elyashevich.ecommerce.command.product;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerce.command.Command;
import org.elyashevich.ecommerce.command.Router;
import org.elyashevich.ecommerce.command.RouterType;
import org.elyashevich.ecommerce.mapper.CategoryMapper;
import org.elyashevich.ecommerce.mapper.ProductMapper;
import org.elyashevich.ecommerce.mapper.impl.CategoryMapperImpl;
import org.elyashevich.ecommerce.mapper.impl.ProductMapperImpl;
import org.elyashevich.ecommerce.service.CategoryService;
import org.elyashevich.ecommerce.service.ProductService;
import org.elyashevich.ecommerce.service.impl.CategoryServiceImpl;
import org.elyashevich.ecommerce.service.impl.ProductServiceImpl;

import java.io.IOException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductSearchActionCommand implements Command {

    @Getter
    private static final ProductSearchActionCommand instance = new ProductSearchActionCommand();

    private final ProductService productService = ProductServiceImpl.getInstance();
    private final ProductMapper productMapper = ProductMapperImpl.getInstance();
    private final CategoryMapper categoryMapper = CategoryMapperImpl.getInstance();
    private final CategoryService categoryService = CategoryServiceImpl.getInstance();

    @Override
    public Router execute(final HttpServletRequest request) throws ServletException, IOException {
        var router = new Router();
        var products = this.productService.findByQuery(request.getParameter("query"));
        var categories = this.categoryService.findAll();
        request.setAttribute("products", this.productMapper.toDto(products));
        request.setAttribute("categories", this.categoryMapper.toDto(categories));
        router.setPath("products");
        router.setType(RouterType.FORWARD);
        return router;
    }
}
