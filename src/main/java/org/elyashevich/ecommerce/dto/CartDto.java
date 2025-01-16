package org.elyashevich.ecommerce.dto;

import jakarta.validation.constraints.NotNull;
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
public class CartDto {

    private Long id;

    @NotNull(message = "User id must be not null.")
    private Long userId;

    @NotNull(message = "Product id must be not null.")
    private Long productId;
}
