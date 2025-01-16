package org.elyashevich.ecommerce.command.register;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerce.command.Command;
import org.elyashevich.ecommerce.command.Router;
import org.elyashevich.ecommerce.command.RouterType;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RegisterViewCommand implements Command {

    @Getter
    private static final RegisterViewCommand instance = new RegisterViewCommand();

    @Override
    public Router execute(final HttpServletRequest request) {
        var router = new Router();
        router.setPath("register");
        router.setType(RouterType.FORWARD);
        return router;
    }
}
