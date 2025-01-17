package org.elyashevich.ecommerce.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@Builder
@ToString(exclude = "password")
@EqualsAndHashCode
public class RegisterDto {
    @NotNull(message = "Username must be not null.")
    @Length(min = 2, message = "Username must contain more then {min} characters.")
    private String username;

    @NotNull(message = "Email must be not null.")
    @Length(min = 5, message = "Email must contain more then {min} characters.")
    @Email(message = "Invalid email format.")
    private String email;

    @NotNull(message = "Password must be not null.")
    @Length(min = 8, message = "Password must contain more then {min} characters.")
    private String password;

    @NotNull(message = "FullName must be not null.")
    @Length(min = 5, message = "FullName must contain more then {min} characters.")
    private String fullName;

    @NotNull(message = "Address must be not null.")
    @Length(min = 4, message = "Address must contain more then {min} characters.")
    private String address;
}
