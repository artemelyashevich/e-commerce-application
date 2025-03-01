package org.elyashevich.ecommerce.command.cart;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerce.command.Command;
import org.elyashevich.ecommerce.command.Router;
import org.elyashevich.ecommerce.command.RouterType;
import org.elyashevich.ecommerce.dto.CartDto;
import org.elyashevich.ecommerce.mapper.CartMapper;
import org.elyashevich.ecommerce.mapper.impl.CartMapperImpl;
import org.elyashevich.ecommerce.service.CartService;
import org.elyashevich.ecommerce.service.impl.CartServiceImpl;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CartAddProductCommand implements Command {

    @Getter
    private static final CartAddProductCommand instance = new CartAddProductCommand();

    private final CartMapper cartMapper = CartMapperImpl.getInstance();
    private final CartService cartService = CartServiceImpl.getInstance();

    @Override
    public Router execute(final HttpServletRequest request) {
        var router = new Router();
        var userId = (Long) request.getSession().getAttribute("userId");
        var cartDto = CartDto.builder()
                .userId(userId)
                .productId(Long.parseLong(request.getParameter("productId")))
                .build();
        this.cartService.addProduct(this.cartMapper.toEntity(cartDto));
        router.setPath("products");
        router.setType(RouterType.REDIRECT);
        return router;
    }
}
