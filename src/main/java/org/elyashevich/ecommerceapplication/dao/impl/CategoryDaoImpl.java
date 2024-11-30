package org.elyashevich.ecommerceapplication.dao.impl;

import org.elyashevich.ecommerceapplication.dao.CategoryDao;
import org.elyashevich.ecommerceapplication.db.ConnectionPool;
import org.elyashevich.ecommerceapplication.entity.AbstractEntity;
import org.elyashevich.ecommerceapplication.entity.Category;
import org.elyashevich.ecommerceapplication.exception.DaoException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {

    private static final String ERROR_TEMPLATE = "Transaction declined: %s";
    private static final String INSERT_QUERY = "INSERT INTO categories (name) VALUES (?);";
    private static final String SELECT_ALL_QUERY = "SELECT id, name FROM categories;";
    private static final String UPDATE_QUERY = """
            UPDATE categories
            SET name = ?,
            WHERE category_id = ?;
            """;
    private static final String DELETE_QUERY = """
            DELETE FROM categories
            WHERE id = ?;
            """;

    @Override
    public void create(final Category category) {
        try (var connection = ConnectionPool.get();
             var prepareStatement = connection.prepareStatement(INSERT_QUERY)) {
            connection.setAutoCommit(false);
            prepareStatement.setString(1, category.getName());
            prepareStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new DaoException(ERROR_TEMPLATE.formatted(e.getMessage()));
        }
    }

    @Override
    public List<Category> findAll() {
        var categories = new ArrayList<Category>();
        try (var connection = ConnectionPool.get();
             var prepareStatement = connection.prepareStatement(SELECT_ALL_QUERY);
             var resultSet = prepareStatement.executeQuery()
        ) {
            while (resultSet.next()) {
                var category = Category.builder()
                        .id(resultSet.getLong(1))
                        .name(resultSet.getString(2))
                        .build();
                categories.add(category);
            }
        } catch (SQLException e) {
            throw new DaoException(ERROR_TEMPLATE.formatted(e.getMessage()));
        }
        return categories;
    }

    @Override
    public Category update(final Long id, final Category category) {
        try (var connection = ConnectionPool.get();
             var prepareStatement = connection.prepareStatement(UPDATE_QUERY)) {
            connection.setAutoCommit(false);
            prepareStatement.setString(1, category.getName());
            prepareStatement.setLong(2, id);
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
             var prepareStatement = connection.prepareStatement(DELETE_QUERY)) {
            connection.setAutoCommit(false);
            prepareStatement.setLong(1, id);
            prepareStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new DaoException(ERROR_TEMPLATE.formatted(e.getMessage()));
        }
    }
}
