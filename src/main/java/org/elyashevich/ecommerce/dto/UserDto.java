package org.elyashevich.ecommerce.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
public class UserDto {

    private Long id;

    private String username;

    private String email;

    private String fullName;

    private String address;

    private String role;
}
