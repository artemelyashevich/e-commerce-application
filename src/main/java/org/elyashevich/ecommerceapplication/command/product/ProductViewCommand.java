package org.elyashevich.ecommerceapplication.command.product;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerceapplication.command.Command;
import org.elyashevich.ecommerceapplication.command.Router;
import org.elyashevich.ecommerceapplication.command.RouterType;
import org.elyashevich.ecommerceapplication.mapper.CategoryMapper;
import org.elyashevich.ecommerceapplication.mapper.ProductMapper;
import org.elyashevich.ecommerceapplication.mapper.impl.CategoryMapperImpl;
import org.elyashevich.ecommerceapplication.mapper.impl.ProductMapperImpl;
import org.elyashevich.ecommerceapplication.service.CategoryService;
import org.elyashevich.ecommerceapplication.service.ProductService;
import org.elyashevich.ecommerceapplication.service.impl.CategoryServiceImpl;
import org.elyashevich.ecommerceapplication.service.impl.ProductServiceImpl;
import org.elyashevich.ecommerceapplication.util.QueryParameterUtil;

import java.io.IOException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductViewCommand implements Command {

    @Getter
    private static final ProductViewCommand instance = new ProductViewCommand();

    private final ProductService productService = ProductServiceImpl.getInstance();
    private final ProductMapper productMapper = ProductMapperImpl.getInstance();
    private final CategoryService categoryService = CategoryServiceImpl.getInstance();
    private final CategoryMapper categoryMapper = CategoryMapperImpl.getInstance();

    @Override
    public Router execute(final HttpServletRequest request) throws ServletException, IOException {
        var router = new Router();
        var id = QueryParameterUtil.getParameters(request).get("id")[0];
        var product = this.productService.findById(Long.parseLong(id));
        var category = this.categoryService.findById(product.getCategoryId());
        request.setAttribute("product", this.productMapper.toDto(product));
        request.setAttribute("category", this.categoryMapper.toDto(category));
        router.setType(RouterType.FORWARD);
        router.setPath("product");
        return router;
    }
}
