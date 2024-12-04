package org.elyashevich.ecommerceapplication.dao.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerceapplication.dao.CartDao;
import org.elyashevich.ecommerceapplication.db.ConnectionPool;
import org.elyashevich.ecommerceapplication.entity.Cart;
import org.elyashevich.ecommerceapplication.exception.DaoException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CartDaoImpl implements CartDao {

    @Getter
    private static final CartDaoImpl instance = new CartDaoImpl();

    private static final String ERROR_TEMPLATE = "Transaction declined: %s";

    private static final String SELECT_ALL_BY_USER = """
            SELECT id, user_id, product_id FROM cart WHERE user_id = ?;
            """;

    private static final String INSERT_QUERY = """
            INSERT INTO cart (user_id, product_id) VALUES (?, ?);
            """;

    private static final String DELETE_QUERY = """
                DELETE cart WHERE id = ?;
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
    public List<Cart> findAllByUser(final Long userId) {
        try (var connection = ConnectionPool.get();
             var prepareStatement = connection.prepareStatement(SELECT_ALL_BY_USER)) {
            var cart = new ArrayList<Cart>();
            prepareStatement.setLong(1, userId);
            try (var resultSet = prepareStatement.executeQuery()) {
                while (resultSet.next()) {
                    var cartItem = Cart.builder()
                            .id(resultSet.getLong(1))
                            .userId(resultSet.getLong(2))
                            .productId(resultSet.getLong(3))
                            .build();
                    cart.add(cartItem);
                }
            }
            return cart;
        } catch (SQLException e) {
            throw new DaoException(ERROR_TEMPLATE.formatted(e.getMessage()));
        }
    }

    @Override
    public void delete(final Long id) {
        try (var connection = ConnectionPool.get();
             var prepareStatement = connection.prepareStatement(DELETE_QUERY)) {
            prepareStatement.setLong(1, id);
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ERROR_TEMPLATE.formatted(e.getMessage()));
        }
    }
}
