package org.elyashevich.ecommerceapplication.command.cart;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerceapplication.command.Command;
import org.elyashevich.ecommerceapplication.command.Router;
import org.elyashevich.ecommerceapplication.command.RouterType;
import org.elyashevich.ecommerceapplication.dto.CartDto;
import org.elyashevich.ecommerceapplication.service.CartService;
import org.elyashevich.ecommerceapplication.service.impl.CartServiceImpl;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CartDeleteProductCommand implements Command {

    @Getter
    private static final CartDeleteProductCommand instance = new CartDeleteProductCommand();

    private final CartService cartService = CartServiceImpl.getInstance();

    @Override
    public Router execute(final HttpServletRequest request) {
        var router = new Router();
        var cartDto = CartDto.builder()
                .productId(Long.parseLong(request.getParameter("productId")))
                .userId(Long.parseLong(request.getParameter("userId")))
                .build();
        this.cartService.deleteProduct(cartDto.getUserId(), cartDto.getProductId());
        router.setPath("cart");
        router.setType(RouterType.REDIRECT);
        return router;
    }
}
