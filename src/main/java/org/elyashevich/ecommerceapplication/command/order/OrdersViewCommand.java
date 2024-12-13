package org.elyashevich.ecommerceapplication.command.order;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerceapplication.command.Command;
import org.elyashevich.ecommerceapplication.command.Router;
import org.elyashevich.ecommerceapplication.command.RouterType;
import org.elyashevich.ecommerceapplication.mapper.OrderMapper;
import org.elyashevich.ecommerceapplication.mapper.impl.OrderMapperImpl;
import org.elyashevich.ecommerceapplication.service.OrderService;
import org.elyashevich.ecommerceapplication.service.impl.OrderServiceImpl;

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
