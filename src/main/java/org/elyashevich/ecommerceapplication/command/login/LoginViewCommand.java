package org.elyashevich.ecommerceapplication.command.login;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerceapplication.command.Command;
import org.elyashevich.ecommerceapplication.command.Router;
import org.elyashevich.ecommerceapplication.command.RouterType;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginViewCommand implements Command {

    @Getter
    private static final LoginViewCommand instance = new LoginViewCommand();

    @Override
    public Router execute(final HttpServletRequest request) {
        var router = new Router();
        router.setPath("login");
        router.setType(RouterType.FORWARD);
        return router;
    }
}
