package org.elyashevich.ecommerceapplication.command.product;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerceapplication.command.Command;
import org.elyashevich.ecommerceapplication.command.Router;
import org.elyashevich.ecommerceapplication.command.RouterType;
import org.elyashevich.ecommerceapplication.entity.Product;
import org.elyashevich.ecommerceapplication.mapper.CategoryMapper;
import org.elyashevich.ecommerceapplication.mapper.ProductMapper;
import org.elyashevich.ecommerceapplication.mapper.impl.CategoryMapperImpl;
import org.elyashevich.ecommerceapplication.mapper.impl.ProductMapperImpl;
import org.elyashevich.ecommerceapplication.service.CategoryService;
import org.elyashevich.ecommerceapplication.service.ProductService;
import org.elyashevich.ecommerceapplication.service.UploadService;
import org.elyashevich.ecommerceapplication.service.impl.CategoryServiceImpl;
import org.elyashevich.ecommerceapplication.service.impl.ProductServiceImpl;
import org.elyashevich.ecommerceapplication.service.impl.UploadServiceImpl;

import java.io.IOException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductCreateActionCommand implements Command {

    @Getter
    private static final ProductCreateActionCommand instance = new ProductCreateActionCommand();
    private final ProductService productService = ProductServiceImpl.getInstance();
    private final ProductMapper productMapper = ProductMapperImpl.getInstance();
    private final UploadService uploadService = UploadServiceImpl.getInstance();

    @Override
    public Router execute(final HttpServletRequest request) throws ServletException, IOException {
        var router = new Router();
        var id = (Long) request.getAttribute("productId");
        var filePart = request.getPart("file");
        var applicationPath = request.getServletContext().getRealPath("");
        var path = this.uploadService.saveProductImage(id, filePart, applicationPath);
        var productDto = this.productMapper.toDto(
                Product.builder()
                        .name(request.getParameter("name"))
                        .description(request.getParameter("description"))
                        .price(Double.parseDouble(request.getParameter("price")))
                        .categoryId(Long.parseLong(request.getParameter("categoryId")))
                        .image(path)
                        .build()
        );
        this.productService.create(this.productMapper.toEntity(productDto));
        router.setPath("products");
        router.setType(RouterType.REDIRECT);
        return router;
    }
}
