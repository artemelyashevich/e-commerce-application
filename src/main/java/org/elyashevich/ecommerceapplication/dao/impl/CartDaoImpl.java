package org.elyashevich.ecommerceapplication.dao.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerceapplication.dao.CartDao;
import org.elyashevich.ecommerceapplication.dao.mapper.RowMapper;
import org.elyashevich.ecommerceapplication.dao.mapper.impl.CartRowMapper;
import org.elyashevich.ecommerceapplication.db.ConnectionPool;
import org.elyashevich.ecommerceapplication.entity.Cart;
import org.elyashevich.ecommerceapplication.exception.DaoException;
import org.elyashevich.ecommerceapplication.util.DaoErrorUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.elyashevich.ecommerceapplication.util.DaoErrorUtil.ERROR_TEMPLATE;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CartDaoImpl implements CartDao {

    @Getter
    private static final CartDaoImpl instance = new CartDaoImpl();

    private static final String SELECT_ALL_BY_USER = """
            SELECT id, user_id, product_id FROM cart WHERE user_id = ?;
            """;
    private static final String INSERT_QUERY = """
            INSERT INTO cart (user_id, product_id) VALUES (?, ?);
            """;
    private static final String DELETE_QUERY = """
                DELETE FROM cart WHERE user_id = ? AND product_id = ?;
            """;

    @Override
    public void create(final Cart cart) {
        try (var connection = ConnectionPool.get();
             var prepareStatement = connection.prepareStatement(INSERT_QUERY)) {
            prepareStatement.setLong(1, cart.getUserId());
            prepareStatement.setLong(2, cart.getProductId());
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ERROR_TEMPLATE.formatted(e.getMessage()));
        }
    }

    @Override
    public void delete(final Long userId, final Long productId) {
        try (var connection = ConnectionPool.get();
             var prepareStatement = connection.prepareStatement(DELETE_QUERY)) {
            prepareStatement.setLong(1, userId);
            prepareStatement.setLong(2, productId);
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ERROR_TEMPLATE.formatted(e.getMessage()));
        }
    }
}
