package org.elyashevich.ecommerceapplication.command.cart;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerceapplication.command.Command;
import org.elyashevich.ecommerceapplication.command.Router;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CartAddProductCommand implements Command {

    @Getter
    private static final CartAddProductCommand instance = new CartAddProductCommand();

    @Override
    public Router execute(final HttpServletRequest request) {
        return null;
    }
}
