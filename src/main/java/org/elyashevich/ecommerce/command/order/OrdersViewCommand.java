package org.elyashevich.ecommerce.command.order;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerce.command.Command;
import org.elyashevich.ecommerce.command.Router;
import org.elyashevich.ecommerce.command.RouterType;
import org.elyashevich.ecommerce.mapper.OrderMapper;
import org.elyashevich.ecommerce.mapper.impl.OrderMapperImpl;
import org.elyashevich.ecommerce.service.OrderService;
import org.elyashevich.ecommerce.service.impl.OrderServiceImpl;

import java.io.IOException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrdersViewCommand implements Command {

    @Getter
    private static final OrdersViewCommand instance = new OrdersViewCommand();

    private final OrderService orderService = OrderServiceImpl.getInstance();
    private final OrderMapper orderMapper = OrderMapperImpl.getInstance();

    @Override
    public Router execute(final HttpServletRequest request) throws ServletException, IOException {
        var router = new Router();
        var orders = this.orderService.findAll();
        request.setAttribute("orders", this.orderMapper.toDto(orders));
        router.setType(RouterType.FORWARD);
        router.setPath("orders");
        return router;
    }
}
