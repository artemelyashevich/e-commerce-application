package org.elyashevich.ecommerce.command.product;

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

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductsViewCommand implements Command {

    @Getter
    private static final ProductsViewCommand instance = new ProductsViewCommand();

    private final ProductService productService = ProductServiceImpl.getInstance();
    private final ProductMapper productMapper = ProductMapperImpl.getInstance();
    private final CategoryService categoryService = CategoryServiceImpl.getInstance();
    private final CategoryMapper categoryMapper = CategoryMapperImpl.getInstance();

    @Override
    public Router execute(final HttpServletRequest request) {
        var router = new Router();
        var products = this.productService.findAll();
        var categories = this.categoryService.findAll();
        request.setAttribute("products", this.productMapper.toDto(products));
        request.setAttribute("categories", this.categoryMapper.toDto(categories));
        router.setType(RouterType.FORWARD);
        router.setPath("products");
        return router;
    }
}
