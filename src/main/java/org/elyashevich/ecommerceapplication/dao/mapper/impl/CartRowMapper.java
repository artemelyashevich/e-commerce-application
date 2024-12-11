package org.elyashevich.ecommerceapplication.dao.mapper.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerceapplication.dao.mapper.RowMapper;
import org.elyashevich.ecommerceapplication.entity.Cart;

import java.sql.ResultSet;
import java.sql.SQLException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CartRowMapper implements RowMapper<Cart> {

    @Getter
    private static final CartRowMapper instance = new CartRowMapper();

    @Override
    public Cart mapRow(final ResultSet rs) throws SQLException {
        return Cart.builder()
                .userId(rs.getLong("user_id"))
                .productId(rs.getLong("product_id"))
                .id(rs.getLong("id"))
                .build();
    }
}
