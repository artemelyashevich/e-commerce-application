package org.elyashevich.ecommerceapplication.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
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
public class LoginDto {

    @NotNull(message = "Email must be not null.")
    @Length(min = 5, message = "Email must contain more then {min} characters.")
    @Email(message = "Invalid email format.")
    private String email;

    @NotNull(message = "Password must be not null.")
    @Length(min = 8, message = "Password must contain more then {min} characters.")
    private String password;
}