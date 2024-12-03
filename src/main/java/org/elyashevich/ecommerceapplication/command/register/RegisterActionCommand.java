package org.elyashevich.ecommerceapplication.command.register;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerceapplication.command.Command;
import org.elyashevich.ecommerceapplication.command.Router;
import org.elyashevich.ecommerceapplication.mapper.RegisterMapper;
import org.elyashevich.ecommerceapplication.mapper.impl.RegisterMapperImpl;
import org.elyashevich.ecommerceapplication.service.AuthService;
import org.elyashevich.ecommerceapplication.service.impl.AuthServiceImpl;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RegisterActionCommand implements Command {

    @Getter
    private static final RegisterActionCommand instance = new RegisterActionCommand();

    private final AuthService authService = AuthServiceImpl.getInstance();
    private final RegisterMapper registerMapper = RegisterMapperImpl.getInstance();

    @Override
    public Router execute(final HttpServletRequest request){
        return null;
    }
}
