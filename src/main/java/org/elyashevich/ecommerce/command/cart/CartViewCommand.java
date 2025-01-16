package org.elyashevich.ecommerce.command.cart;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerce.command.Command;
import org.elyashevich.ecommerce.command.Router;
import org.elyashevich.ecommerce.command.RouterType;
import org.elyashevich.ecommerce.mapper.ProductMapper;
import org.elyashevich.ecommerce.mapper.impl.ProductMapperImpl;
import org.elyashevich.ecommerce.service.ProductService;
import org.elyashevich.ecommerce.service.impl.ProductServiceImpl;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CartViewCommand implements Command {

    @Getter
    private static final CartViewCommand instance = new CartViewCommand();

    private final ProductService productService = ProductServiceImpl.getInstance();
    private final ProductMapper productMapper = ProductMapperImpl.getInstance();

    @Override
    public Router execute(final HttpServletRequest request) {
        var router = new Router();
        var userId = (Long) request.getSession().getAttribute("userId");
        var cartList = this.productService.findFromCartByUser(userId);
        request.setAttribute("cart", this.productMapper.toDto(cartList));
        router.setType(RouterType.FORWARD);
        router.setPath("cart");
        return router;
    }
}
