package org.elyashevich.ecommerce.mapper.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerce.dto.UserDto;
import org.elyashevich.ecommerce.entity.User;
import org.elyashevich.ecommerce.mapper.UserMapper;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapperImpl implements UserMapper {

    @Getter
    private static final UserMapper instance = new UserMapperImpl();

    @Override
    public UserDto toDto(final User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .fullName(user.getFullName())
                .address(user.getAddress())
                .role(user.getRole().getName())
                .build();
    }

    @Override
    public List<UserDto> toDto(final List<User> entities) {
        return entities.stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public User toEntity(final UserDto userDto) {
        return User.builder()
                .build();
    }
}
