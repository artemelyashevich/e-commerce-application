package org.elyashevich.ecommerceapplication.command.users;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerceapplication.command.Command;
import org.elyashevich.ecommerceapplication.command.Router;
import org.elyashevich.ecommerceapplication.command.RouterType;
import org.elyashevich.ecommerceapplication.mapper.UserMapper;
import org.elyashevich.ecommerceapplication.mapper.impl.UserMapperImpl;
import org.elyashevich.ecommerceapplication.service.UserService;
import org.elyashevich.ecommerceapplication.service.impl.UserServiceImpl;

import java.io.IOException;

@NoArgsConstructor
public class UsersViewCommand implements Command {

    @Getter
    private static final UsersViewCommand instance = new UsersViewCommand();

    private final UserService userService = UserServiceImpl.getInstance();
    private final UserMapper userMapper = UserMapperImpl.getInstance();

    @Override
    public Router execute(final HttpServletRequest request) throws ServletException, IOException {
        var router = new Router();
        var users = this.userService.findAll();
        request.setAttribute("users", this.userMapper.toDto(users));
        router.setType(RouterType.FORWARD);
        router.setPath("users");
        return router;
    }
}
