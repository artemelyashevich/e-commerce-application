package org.elyashevich.ecommerceapplication.mapper.impl;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerceapplication.dto.CartDto;
import org.elyashevich.ecommerceapplication.entity.Cart;
import org.elyashevich.ecommerceapplication.mapper.CartMapper;

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
