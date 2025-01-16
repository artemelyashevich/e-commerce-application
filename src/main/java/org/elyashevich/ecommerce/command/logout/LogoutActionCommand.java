package org.elyashevich.ecommerce.command.logout;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerce.command.Command;
import org.elyashevich.ecommerce.command.Router;
import org.elyashevich.ecommerce.command.RouterType;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LogoutActionCommand implements Command {

    @Getter
    private static final LogoutActionCommand instance = new LogoutActionCommand();

    @Override
    public Router execute(final HttpServletRequest request) {
        var router = new Router();
        request.getSession().removeAttribute("userId");
        request.getSession().removeAttribute("role");
        router.setPath("login");
        router.setType(RouterType.REDIRECT);
        return router;
    }
}
