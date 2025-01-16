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
public class ProductFilterActionCommand implements Command {

    @Getter
    private static final ProductFilterActionCommand instance = new ProductFilterActionCommand();

    private final ProductService productService = ProductServiceImpl.getInstance();
    private final ProductMapper productMapper = ProductMapperImpl.getInstance();
    private final CategoryService categoryService = CategoryServiceImpl.getInstance();
    private final CategoryMapper categoryMapper = CategoryMapperImpl.getInstance();

    @Override
    public Router execute(final HttpServletRequest request) throws ServletException, IOException {
        var router = new Router();
        var categoryId = request.getParameter("categoryId");
        var products = this.productService.findByCategoryId(Long.parseLong(categoryId));
        var categories = this.categoryService.findAll();
        request.setAttribute("categories", this.categoryMapper.toDto(categories));
        request.setAttribute("products", this.productMapper.toDto(products));
        router.setPath("products");
        router.setType(RouterType.FORWARD);
        return router;
    }
}
