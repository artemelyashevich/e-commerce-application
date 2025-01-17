package org.elyashevich.ecommerce.dto;

import javax.validation.constraints.NotNull;
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
public class CategoryDto{
        private Long id;

        @NotNull(message = "Name must be not null.")
        private String name;
}
