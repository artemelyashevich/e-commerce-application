package org.elyashevich.ecommerceapplication.dao.mapper.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerceapplication.dao.mapper.RowMapper;
import org.elyashevich.ecommerceapplication.entity.Role;
import org.elyashevich.ecommerceapplication.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserRowMapper implements RowMapper<User> {

    @Getter
    private static final UserRowMapper instance = new UserRowMapper();

    @Override
    public User mapRow(final ResultSet rs) throws SQLException {
        return User.builder()
                .id(rs.getLong("id"))
                .username(rs.getString("username"))
                .fullName(rs.getString("full_name"))
                .address(rs.getString("address"))
                .email(rs.getString("email"))
                .password(rs.getString("password"))
                .role(Role.builder().name(rs.getString("role_name")).build())
                .build();
    }
}
