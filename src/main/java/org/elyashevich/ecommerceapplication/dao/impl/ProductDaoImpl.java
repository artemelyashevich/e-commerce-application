package org.elyashevich.ecommerceapplication.dao.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerceapplication.dao.ProductDao;
import org.elyashevich.ecommerceapplication.db.ConnectionPool;
import org.elyashevich.ecommerceapplication.entity.Product;
import org.elyashevich.ecommerceapplication.exception.DaoException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductDaoImpl implements ProductDao {

    @Getter
    private static final ProductDaoImpl instance = new ProductDaoImpl();

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

    private static final String SELECT_FROM_CART = """
            SELECT id, name, description, price, category_id
            FROM products
            JOIN cart ON cart.product_id = products.id
            WHERE user_id = ?;
            """;

    private static final String SET_IMAGE_QUERY = """
            UPDATE products
            SET image = ?
            WHERE id = ?;
            """;

    @Override
    public void create(final Product product) {
        try (var connection = ConnectionPool.get();
             var prepareStatement = connection.prepareStatement(INSERT_QUERY)) {
            prepareStatement.setString(1, product.getName());
            prepareStatement.setString(2, product.getDescription());
            prepareStatement.setDouble(3, product.getPrice());
            prepareStatement.setLong(4, product.getCategoryId());
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ERROR_TEMPLATE.formatted(e.getMessage()));
        }
    }

    @Override
    public List<Product> findAll() {
        try (var connection = ConnectionPool.get();
             var prepareStatement = connection.prepareStatement(SELECT_ALL_QUERY);
             var resultSet = prepareStatement.executeQuery()
        ) {
            var products = new ArrayList<Product>();
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
            return products;
        } catch (SQLException e) {
            throw new DaoException(ERROR_TEMPLATE.formatted(e.getMessage()));
        }
    }

    @Override
    public void update(final Long id, final Product product) {
        try (var connection = ConnectionPool.get();
             var prepareStatement = connection.prepareStatement(UPDATE_QUERY)
        ) {
            prepareStatement.setString(1, product.getName());
            prepareStatement.setString(2, product.getDescription());
            prepareStatement.setDouble(3, product.getPrice());
            prepareStatement.setLong(4, product.getCategoryId());
            prepareStatement.setLong(5, id);
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ERROR_TEMPLATE.formatted(e.getMessage()));
        }
    }

    @Override
    public void delete(final Long id) {
        try (var connection = ConnectionPool.get();
             var preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ERROR_TEMPLATE.formatted(e.getMessage()));
        }
    }

    @Override
    public List<Product> findFromCart(final Long userId) {
        try (var connection = ConnectionPool.get();
             var prepareStatement = connection.prepareStatement(SELECT_FROM_CART)) {
            var products = new ArrayList<Product>();
            prepareStatement.setLong(1, userId);
            try (var resultSet = prepareStatement.executeQuery()) {
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
            }
            return products;
        } catch (SQLException e) {
            throw new DaoException(ERROR_TEMPLATE.formatted(e.getMessage()));
        }
    }

    @Override
    public void setImage(final Long id, final String filePath) {
        try (var connection = ConnectionPool.get();
             var prepareStatement = connection.prepareStatement(SET_IMAGE_QUERY)) {
            prepareStatement.setString(1, filePath);
            prepareStatement.setLong(2, id);
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ERROR_TEMPLATE.formatted(e.getMessage()));
        }
    }
}
