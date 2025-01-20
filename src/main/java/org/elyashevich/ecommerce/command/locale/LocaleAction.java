package org.elyashevich.ecommerce.command.locale;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerce.command.Command;
import org.elyashevich.ecommerce.command.Router;
import org.elyashevich.ecommerce.command.RouterType;

import java.io.IOException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LocaleAction implements Command {

    @Getter
    private static final LocaleAction instance = new LocaleAction();

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        var router = new Router();
        var reqLocale = request.getParameter("locale");
        router.setType(RouterType.FORWARD);
        router.setPath(request.getRequestURI());
        return router;
    }
}
