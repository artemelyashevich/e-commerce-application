package org.elyashevich.ecommerceapplication.command.cart;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerceapplication.command.Command;
import org.elyashevich.ecommerceapplication.command.Router;
import org.elyashevich.ecommerceapplication.command.RouterType;
import org.elyashevich.ecommerceapplication.dto.CartDto;
import org.elyashevich.ecommerceapplication.entity.User;
import org.elyashevich.ecommerceapplication.mapper.CartMapper;
import org.elyashevich.ecommerceapplication.mapper.impl.CartMapperImpl;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CartAddProductCommand implements Command {

    @Getter
    private static final CartAddProductCommand instance = new CartAddProductCommand();

    private final CartMapper cartMapper = CartMapperImpl.getInstance();

    @Override
    public Router execute(final HttpServletRequest request) {
        var router = new Router();
        var user = (User) request.getSession().getAttribute("user");
        System.out.println(user);
        var cartDto = CartDto.builder()
                .userId(user.getId())
                .productId(Long.parseLong(request.getParameter("productId")))
                .build();
        System.out.println(cartDto);
        router.setPath("products");
        router.setType(RouterType.FORWARD);
        return router;
    }
}
