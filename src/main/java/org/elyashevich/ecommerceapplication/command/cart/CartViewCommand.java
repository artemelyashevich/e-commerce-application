package org.elyashevich.ecommerceapplication.command.cart;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerceapplication.command.Command;
import org.elyashevich.ecommerceapplication.command.Router;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CartViewCommand implements Command {

    @Getter
    private static final CartViewCommand instance = new CartViewCommand();

    @Override
    public Router execute(final HttpServletRequest request) {
        return null;
    }
}
