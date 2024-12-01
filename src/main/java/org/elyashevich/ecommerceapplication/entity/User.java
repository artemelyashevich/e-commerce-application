package org.elyashevich.ecommerceapplication.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@Builder
@ToString(exclude = "password")
@EqualsAndHashCode(callSuper = false)
public class User extends AbstractEntity {

    private Long id;

    private String username;

    private String email;

    private String password;

    private String fullName;

    private String address;

    private List<Role> roles;
}
