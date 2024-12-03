package org.elyashevich.ecommerceapplication.command.login;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerceapplication.command.Command;
import org.elyashevich.ecommerceapplication.command.Router;
import org.elyashevich.ecommerceapplication.command.RouterType;
import org.elyashevich.ecommerceapplication.dto.LoginDto;
import org.elyashevich.ecommerceapplication.mapper.LoginMapper;
import org.elyashevich.ecommerceapplication.mapper.impl.LoginMapperImpl;
import org.elyashevich.ecommerceapplication.service.AuthService;
import org.elyashevich.ecommerceapplication.service.impl.AuthServiceImpl;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginActionCommand implements Command {

    @Getter
    private static final LoginActionCommand instance = new LoginActionCommand();

    private final AuthService authService = AuthServiceImpl.getInstance();
    private final LoginMapper loginMapper = LoginMapperImpl.getInstance();

    @Override
    public Router execute(final HttpServletRequest request) {
        var router = new Router();
        var loginDto = LoginDto.builder()
                .email(request.getParameter("email"))
                .password(request.getParameter("password"))
                .build();
        try {
            var isLoggedIn = this.authService.login(this.loginMapper.toEntity(loginDto));
            if (!isLoggedIn) {
                router.setType(RouterType.FORWARD);
                router.setPath("login");
                return router;
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }

        router.setType(RouterType.FORWARD);
        router.setPath("products");
        return router;
    }
}
