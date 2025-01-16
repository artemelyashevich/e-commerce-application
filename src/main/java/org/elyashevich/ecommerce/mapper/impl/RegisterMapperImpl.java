package org.elyashevich.ecommerce.mapper.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerce.dto.RegisterDto;
import org.elyashevich.ecommerce.entity.User;
import org.elyashevich.ecommerce.mapper.RegisterMapper;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RegisterMapperImpl implements RegisterMapper {

    @Getter
    private static final RegisterMapperImpl instance = new RegisterMapperImpl();

    @Override
    public User toEntity(final RegisterDto dto) {
        return User.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .fullName(dto.getFullName())
                .address(dto.getAddress())
                .password(dto.getPassword())
                .build();
    }
}
