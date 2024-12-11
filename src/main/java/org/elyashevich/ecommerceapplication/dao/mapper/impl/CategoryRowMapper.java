package org.elyashevich.ecommerceapplication.dao.mapper.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerceapplication.dao.mapper.RowMapper;
import org.elyashevich.ecommerceapplication.entity.Category;

import java.sql.ResultSet;
import java.sql.SQLException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryRowMapper implements RowMapper<Category> {

    @Getter
    private static final CategoryRowMapper instance = new CategoryRowMapper();

    @Override
    public Category mapRow(final ResultSet rs) throws SQLException {
        return Category.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .build();
    }
}
