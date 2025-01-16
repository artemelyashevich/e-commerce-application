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
public class OrderDeleteActionCommand implements Command {

    @Getter
    private static final OrderDeleteActionCommand instance = new OrderDeleteActionCommand();

    private final OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public Router execute(final HttpServletRequest request) throws ServletException, IOException {
        var router = new Router();
        var orderId = request.getParameter("orderId");
        this.orderService.delete(Long.parseLong(orderId));
        router.setType(RouterType.REDIRECT);
        router.setPath("orders");
        return router;
    }
}
