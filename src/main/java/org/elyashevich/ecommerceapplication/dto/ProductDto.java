package org.elyashevich.ecommerceapplication.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
public class ProductDto {

        private Long id;

        @NotNull(message = "Name must be not null.")
        @Length(
                min = 2,
                max = 255,
                message = "Name must be in {min} - {max}."
        )
        private String name;

        @NotNull(message = "Description must be not null.")
        @Length(
                min = 2,
                max = 5000,
                message = "Description must be in {min} - {max}."
        )
        private String description;

        @NotNull(message = "Price must be not null.")
        @Positive(message = "Price must be positive.")
        @Min(value = 0, message = "Price must be more then {value}.")
        @Max(value = Long.MAX_VALUE, message = "Price must me less then {value}.")
        private Double price;

        // TODO: type -> CategoryDto
        private Long categoryId;
}
