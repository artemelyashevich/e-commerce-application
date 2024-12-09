package org.elyashevich.ecommerceapplication.command.users;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerceapplication.command.Command;
import org.elyashevich.ecommerceapplication.command.Router;
import org.elyashevich.ecommerceapplication.command.RouterType;

import java.io.IOException;

@NoArgsConstructor
public class UsersViewCommand implements Command {

    @Getter
    private static final UsersViewCommand instance = new UsersViewCommand();

    @Override
    public Router execute(final HttpServletRequest request) throws ServletException, IOException {
        var router = new Router();
        router.setType(RouterType.FORWARD);
        router.setPath("users");
        return router;
    }
}
