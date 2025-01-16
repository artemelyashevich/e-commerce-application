package org.elyashevich.ecommerce.command.product;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerce.command.Command;
import org.elyashevich.ecommerce.command.Router;
import org.elyashevich.ecommerce.command.RouterType;
import org.elyashevich.ecommerce.entity.Product;
import org.elyashevich.ecommerce.mapper.ProductMapper;
import org.elyashevich.ecommerce.mapper.impl.ProductMapperImpl;
import org.elyashevich.ecommerce.service.ProductService;
import org.elyashevich.ecommerce.service.UploadService;
import org.elyashevich.ecommerce.service.impl.ProductServiceImpl;
import org.elyashevich.ecommerce.service.impl.UploadServiceImpl;

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
