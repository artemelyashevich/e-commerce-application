package org.elyashevich.ecommerceapplication.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;

public record ProductDto(

        Long id,

        @NotNull(message = "Name must be not null.")
        @Length(
                min = 2,
                max = 255,
                message = "Name must be in {min} - {max}."
        )
        String name,

        @NotNull(message = "Description must be not null.")
        @Length(
                min = 2,
                max = 5000,
                message = "Description must be in {min} - {max}."
        )
        String description,

        @NotNull(message = "Price must be not null.")
        @Positive(message = "Price must be positive.")
        @Min(value = 0, message = "Price must be more then {value}.")
        @Max(value = Long.MAX_VALUE, message = "Price must me less then {value}.")
        Double price,

        // TODO: type -> CategoryDto
        Long categoryId
) {
}
