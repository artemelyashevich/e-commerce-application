package org.elyashevich.ecommerceapplication.command.cart;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerceapplication.command.Command;
import org.elyashevich.ecommerceapplication.command.Router;
import org.elyashevich.ecommerceapplication.command.RouterType;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CartViewCommand implements Command {

    @Getter
    private static final CartViewCommand instance = new CartViewCommand();

    @Override
    public Router execute(final HttpServletRequest request) {
        var router = new Router();
        router.setType(RouterType.FORWARD);
        router.setPath("cart");
        return router;
    }
}
