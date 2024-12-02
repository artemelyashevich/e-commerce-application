package org.elyashevich.ecommerceapplication.mapper.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerceapplication.dto.LoginDto;
import org.elyashevich.ecommerceapplication.entity.User;
import org.elyashevich.ecommerceapplication.mapper.LoginMapper;

import java.util.List;

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
