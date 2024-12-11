package org.elyashevich.ecommerceapplication.dao.mapper.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerceapplication.dao.mapper.RowMapper;
import org.elyashevich.ecommerceapplication.entity.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RoleRowMapper implements RowMapper<Role> {

    @Getter
    private static final RoleRowMapper instance = new RoleRowMapper();

    @Override
    public Role mapRow(final ResultSet rs) throws SQLException {
        return Role.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .build();
    }
}
