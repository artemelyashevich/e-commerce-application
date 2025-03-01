package org.elyashevich.ecommerce.command.register;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerce.command.Command;
import org.elyashevich.ecommerce.command.Router;
import org.elyashevich.ecommerce.command.RouterType;
import org.elyashevich.ecommerce.dto.RegisterDto;
import org.elyashevich.ecommerce.mapper.RegisterMapper;
import org.elyashevich.ecommerce.mapper.impl.RegisterMapperImpl;
import org.elyashevich.ecommerce.service.AuthService;
import org.elyashevich.ecommerce.service.impl.AuthServiceImpl;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RegisterActionCommand implements Command {

    @Getter
    private static final RegisterActionCommand instance = new RegisterActionCommand();

    private final AuthService authService = AuthServiceImpl.getInstance();
    private final RegisterMapper registerMapper = RegisterMapperImpl.getInstance();

    @Override
    public Router execute(final HttpServletRequest request) {
        var router = new Router();
        var registerDto = RegisterDto.builder()
                .email(request.getParameter("email"))
                .username(request.getParameter("username"))
                .fullName(request.getParameter("fullName"))
                .address(request.getParameter("address"))
                .password(request.getParameter("password"))
                .build();
        try {
            var candidate = this.registerMapper.toEntity(registerDto);
            var dto = this.authService.register(candidate);
            var session = request.getSession();
            session.setAttribute("userId", dto.getId());
            session.setAttribute("role", dto.getRole().getName());
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("");
        }
        router.setType(RouterType.REDIRECT);
        router.setPath("products");
        return router;
    }
}
