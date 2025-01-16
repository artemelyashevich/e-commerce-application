package org.elyashevich.ecommerce.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.elyashevich.ecommerce.entity.Role;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
public class AuthDto {

    private Long id;

    private Role role;
}
