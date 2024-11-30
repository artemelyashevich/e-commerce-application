package org.elyashevich.ecommerceapplication.dao.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerceapplication.dao.BaseDao;
import org.elyashevich.ecommerceapplication.dao.ProductDao;
import org.elyashevich.ecommerceapplication.db.ConnectionPool;
import org.elyashevich.ecommerceapplication.entity.Product;
import org.elyashevich.ecommerceapplication.exception.DaoException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductDaoImpl implements ProductDao {

    private static final String ERROR_TEMPLATE = "Transaction declined: %s";

    private static final String INSERT_QUERY = """
            INSERT INTO products (name, description, price, category_id)
            VALUES (?, ?, ?, ?);
            """;

    private static final String SELECT_ALL_QUERY = "SELECT id, name, description, price, category_id FROM products;";

    private static final String UPDATE_QUERY = """
            UPDATE products
            SET name = ?,
                description = ?,
                price =?,
                category_id = ?
            WHERE id = ?;
            """;

    private static final String DELETE_QUERY = """
            DELETE FROM products
            WHERE id = ?;
            """;

    @Getter
    private static final ProductDaoImpl instance = new ProductDaoImpl();

    @Override
    public void create(final Product product) {
        try (var connection = ConnectionPool.get();
             var prepareStatement = connection.prepareStatement(INSERT_QUERY)) {
            connection.setAutoCommit(false);
            prepareStatement.setString(1, product.getName());
            prepareStatement.setString(2, product.getDescription());
            prepareStatement.setDouble(3, product.getPrice());
            prepareStatement.setLong(4, product.getCategoryId());
            prepareStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new DaoException(ERROR_TEMPLATE.formatted(e.getMessage()));
        }
    }

    @Override
    public List<Product> findAll() {
        var products = new ArrayList<Product>();
        try (var connection = ConnectionPool.get();
             var prepareStatement = connection.prepareStatement(SELECT_ALL_QUERY);
             var resultSet = prepareStatement.executeQuery()
        ) {
            connection.setAutoCommit(false);
            while (resultSet.next()) {
                var product = Product.builder()
                        .id(resultSet.getLong(1))
                        .name(resultSet.getString(2))
                        .description(resultSet.getString(3))
                        .price(resultSet.getDouble(4))
                        .categoryId(resultSet.getLong(5))
                        .build();
                products.add(product);
            }
            connection.commit();
        } catch (SQLException e) {
            throw new DaoException(ERROR_TEMPLATE.formatted(e.getMessage()));
        }
        return products;
    }

    @Override
    public Product update(final Long id, final Product product) {
        try (var connection = ConnectionPool.get();
             var prepareStatement = connection.prepareStatement(UPDATE_QUERY)
        ) {
            connection.setAutoCommit(false);
            prepareStatement.setString(1, product.getName());
            prepareStatement.setString(2, product.getDescription());
            prepareStatement.setDouble(3, product.getPrice());
            prepareStatement.setLong(4, product.getCategoryId());
            prepareStatement.setLong(5, id);
            prepareStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new DaoException(ERROR_TEMPLATE.formatted(e.getMessage()));
        }
        return null;
    }

    @Override
    public void delete(final Long id) {
        try (var connection = ConnectionPool.get();
             var preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
            connection.setAutoCommit(false);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new DaoException(ERROR_TEMPLATE.formatted(e.getMessage()));
        }
    }
}
