package org.elyashevich.ecommerceapplication.mapper.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerceapplication.dto.OrderDto;
import org.elyashevich.ecommerceapplication.entity.Order;
import org.elyashevich.ecommerceapplication.mapper.OrderMapper;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderMapperImpl implements OrderMapper {

    @Getter
    private static final OrderMapperImpl instance = new OrderMapperImpl();

    @Override
    public OrderDto toDto(final Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .userId(order.getUserId())
                .totalAmount(order.getTotalAmount())
                .updatedAt(order.getUpdatedAt())
                .createdAt(order.getCreatedAt())
                .build();
    }

    @Override
    public List<OrderDto> toDto(final List<Order> entities) {
        return entities.stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public Order toEntity(final OrderDto orderDto) {
        return Order.builder()
                .userId(orderDto.getUserId())
                .totalAmount(orderDto.getTotalAmount())
                .build();
    }
}
