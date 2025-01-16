package org.elyashevich.ecommerce.mapper;

import org.elyashevich.ecommerce.dto.RegisterDto;
import org.elyashevich.ecommerce.entity.User;

public interface RegisterMapper {

    User toEntity(final RegisterDto dto);
}
