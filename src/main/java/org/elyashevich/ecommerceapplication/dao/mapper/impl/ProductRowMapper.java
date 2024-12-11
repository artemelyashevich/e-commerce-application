package org.elyashevich.ecommerceapplication.dao.mapper.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerceapplication.dao.mapper.RowMapper;
import org.elyashevich.ecommerceapplication.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductRowMapper implements RowMapper<Product> {

    @Getter
    private static final ProductRowMapper instance = new ProductRowMapper();

    @Override
    public Product mapRow(final ResultSet rs) throws SQLException {
        return Product.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .description(rs.getString("description"))
                .price(rs.getDouble("price"))
                .categoryId(rs.getLong("category_id"))
                .image(rs.getString("image"))
                .build();
    }
}
