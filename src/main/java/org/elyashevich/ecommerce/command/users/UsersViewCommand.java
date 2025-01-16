package org.elyashevich.ecommerce.command.users;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerce.command.Command;
import org.elyashevich.ecommerce.command.Router;
import org.elyashevich.ecommerce.command.RouterType;
import org.elyashevich.ecommerce.mapper.UserMapper;
import org.elyashevich.ecommerce.mapper.impl.UserMapperImpl;
import org.elyashevich.ecommerce.service.UserService;
import org.elyashevich.ecommerce.service.impl.UserServiceImpl;

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
