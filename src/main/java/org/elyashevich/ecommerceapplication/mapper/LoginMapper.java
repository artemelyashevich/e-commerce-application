package org.elyashevich.ecommerceapplication.mapper;

import org.elyashevich.ecommerceapplication.dto.LoginDto;
import org.elyashevich.ecommerceapplication.entity.User;

public interface LoginMapper {

    User toEntity(final LoginDto dto);
}
