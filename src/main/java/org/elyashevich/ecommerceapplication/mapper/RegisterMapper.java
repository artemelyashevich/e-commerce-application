package org.elyashevich.ecommerceapplication.mapper;

import org.elyashevich.ecommerceapplication.dto.RegisterDto;
import org.elyashevich.ecommerceapplication.entity.User;

public interface RegisterMapper {

    User toEntity(final RegisterDto dto);
}
