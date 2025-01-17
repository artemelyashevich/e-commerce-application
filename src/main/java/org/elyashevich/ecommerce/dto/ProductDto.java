package org.elyashevich.ecommerce.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
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
public class ProductDto {

        private Long id;

        @NotNull(message = "Name must be not null.")
        private String name;

        @NotNull(message = "Description must be not null.")
        private String description;

        @NotNull(message = "Price must be not null.")
        @Positive(message = "Price must be positive.")
        @Min(value = 0, message = "Price must be more then {value}.")
        @Max(value = Long.MAX_VALUE, message = "Price must me less then {value}.")
        private Double price;

        // TODO: type -> CategoryDto
        private Long categoryId;

        @NotNull
        private String image;
}
