package org.elyashevich.ecommerceapplication.mapper.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerceapplication.dto.RegisterDto;
import org.elyashevich.ecommerceapplication.entity.User;
import org.elyashevich.ecommerceapplication.mapper.RegisterMapper;

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
