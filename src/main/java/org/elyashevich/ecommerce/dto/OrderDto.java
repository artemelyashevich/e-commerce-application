package org.elyashevich.ecommerce.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
public class OrderDto {

    private Long id;

    @NotNull(message = "User id must be not null")
    private Long userId;

    @NotNull(message = "Total amount must be not null")
    private Double totalAmount;

    private Timestamp createdAt;

    private Timestamp updatedAt;
}
