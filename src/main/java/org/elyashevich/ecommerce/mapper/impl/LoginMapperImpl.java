package org.elyashevich.ecommerce.mapper.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerce.dto.LoginDto;
import org.elyashevich.ecommerce.entity.User;
import org.elyashevich.ecommerce.mapper.LoginMapper;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginMapperImpl implements LoginMapper {

    @Getter
    private static final LoginMapperImpl instance = new LoginMapperImpl();

    @Override
    public User toEntity(final LoginDto dto) {
        return User.builder()
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();
    }
}
