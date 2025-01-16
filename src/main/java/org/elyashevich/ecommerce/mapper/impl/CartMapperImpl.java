package org.elyashevich.ecommerce.mapper.impl;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerce.dto.CartDto;
import org.elyashevich.ecommerce.entity.Cart;
import org.elyashevich.ecommerce.mapper.CartMapper;

import java.util.List;

@NoArgsConstructor
public class CartMapperImpl implements CartMapper {

    @Getter
    public static final CartMapperImpl instance = new CartMapperImpl();

    @Override
    public CartDto toDto(final Cart cart) {
        return CartDto.builder()
                .id(cart.getId())
                .productId(cart.getProductId())
                .userId(cart.getUserId())
                .build();
    }

    @Override
    public List<CartDto> toDto(final List<Cart> entities) {
        return entities.stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public Cart toEntity(final CartDto cartDto) {
        return Cart.builder()
                .productId(cartDto.getProductId())
                .userId(cartDto.getUserId())
                .build();
    }
}
