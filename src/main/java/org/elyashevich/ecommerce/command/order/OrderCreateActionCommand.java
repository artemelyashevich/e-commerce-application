package org.elyashevich.ecommerce.command.order;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerce.command.Command;
import org.elyashevich.ecommerce.command.Router;
import org.elyashevich.ecommerce.command.RouterType;
import org.elyashevich.ecommerce.service.OrderService;
import org.elyashevich.ecommerce.service.impl.OrderServiceImpl;

import java.io.IOException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderCreateActionCommand implements Command {

    @Getter
    private static final OrderCreateActionCommand instance = new OrderCreateActionCommand();

    private final OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public Router execute(final HttpServletRequest request) throws ServletException, IOException {
        var router = new Router();
        var userId = (Long)request.getSession().getAttribute("userId");
        this.orderService.create(userId);
        router.setPath("products");
        router.setType(RouterType.REDIRECT);
        return router;
    }
}
