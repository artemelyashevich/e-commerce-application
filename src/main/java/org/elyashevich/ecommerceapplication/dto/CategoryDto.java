package org.elyashevich.ecommerceapplication.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
public class CategoryDto{
        private Long id;

        @NotNull(message = "Name must be not null.")
        @Length(
                min = 2,
                max = 255,
                message = "Name must be in {min} - {max}"
        )
        private String name;
}
