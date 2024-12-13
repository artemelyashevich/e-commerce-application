package org.elyashevich.ecommerceapplication.dao.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerceapplication.dao.OrderDao;
import org.elyashevich.ecommerceapplication.dao.mapper.RowMapper;
import org.elyashevich.ecommerceapplication.dao.mapper.impl.OrderRowMapper;
import org.elyashevich.ecommerceapplication.db.ConnectionPool;
import org.elyashevich.ecommerceapplication.entity.Order;
import org.elyashevich.ecommerceapplication.exception.DaoException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.elyashevich.ecommerceapplication.util.DaoErrorUtil.ERROR_TEMPLATE;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderDaoImpl implements OrderDao {

    @Getter
    private static final OrderDaoImpl instance = new OrderDaoImpl();

    private static final String SELECT_ALL_QUERY = """
            SELECT id, user_id, total_amount, created_at, updated_at
            FROM orders;
            """;
    private static final String INSERT_QUERY = """
            INSERT INTO orders (user_id, total_amount) 
            VALUES (?, ?);  
             """;
    private static final String UPDATE_QUERY = """
            UPDATE orders 
            SET total_amount = ?
            WHERE id = ?;
            """;
    private static final String DELETE_QUERY = """
            DELETE FROM orders
            WHERE id = ?; 
            """;

    private final RowMapper<Order> orderRowMapper = OrderRowMapper.getInstance();

    @Override
    public void create(final Order order) {
        try (var connection = ConnectionPool.get();
             var prepareStatement = connection.prepareStatement(INSERT_QUERY)) {
            prepareStatement.setLong(1, order.getUserId());
            prepareStatement.setDouble(2, order.getTotalAmount());
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ERROR_TEMPLATE.formatted(e.getMessage()));
        }
    }

    @Override
    public List<Order> findAll() {
        try (var connection = ConnectionPool.get();
             var prepareStatement = connection.prepareStatement(SELECT_ALL_QUERY);
             var resultSet = prepareStatement.executeQuery()) {
            var orders = new ArrayList<Order>();
            while (resultSet.next()) {
                orders.add(this.orderRowMapper.mapRow(resultSet));
            }
            return orders;
        } catch (SQLException e) {
            throw new DaoException(ERROR_TEMPLATE.formatted(e.getMessage()));
        }
    }

    @Override
    public void update(final Long id, final Order order) {
        try (var connection = ConnectionPool.get();
             var prepareStatement = connection.prepareStatement(UPDATE_QUERY)) {
            prepareStatement.setDouble(1, order.getTotalAmount());
            prepareStatement.setLong(2, id);
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
