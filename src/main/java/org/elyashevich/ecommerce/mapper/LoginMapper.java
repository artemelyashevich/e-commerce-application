package org.elyashevich.ecommerce.mapper;

import org.elyashevich.ecommerce.dto.LoginDto;
import org.elyashevich.ecommerce.entity.User;

public interface LoginMapper {

    User toEntity(final LoginDto dto);
}
