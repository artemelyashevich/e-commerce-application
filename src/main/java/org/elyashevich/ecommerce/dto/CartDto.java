package org.elyashevich.ecommerce.dto;


import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
public class CartDto {

    private Long id;

    @NotNull(message = "User id must be not null.")
    private Long userId;

    @NotNull(message = "Product id must be not null.")
    private Long productId;
}
