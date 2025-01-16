package org.elyashevich.ecommerce.command.login;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerce.command.Command;
import org.elyashevich.ecommerce.command.Router;
import org.elyashevich.ecommerce.command.RouterType;

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
