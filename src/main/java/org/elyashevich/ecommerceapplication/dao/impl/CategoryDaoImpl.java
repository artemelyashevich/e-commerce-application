package org.elyashevich.ecommerceapplication.dao.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerceapplication.dao.CategoryDao;
import org.elyashevich.ecommerceapplication.dao.mapper.RowMapper;
import org.elyashevich.ecommerceapplication.dao.mapper.impl.CategoryRowMapper;
import org.elyashevich.ecommerceapplication.db.ConnectionPool;
import org.elyashevich.ecommerceapplication.entity.Category;
import org.elyashevich.ecommerceapplication.exception.DaoException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.elyashevich.ecommerceapplication.util.DaoErrorUtil.ERROR_TEMPLATE;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryDaoImpl implements CategoryDao {

    @Getter
    private static final CategoryDaoImpl instance = new CategoryDaoImpl();

    private static final String INSERT_QUERY = "INSERT INTO categories (name) VALUES (?);";
    private static final String SELECT_ALL_QUERY = "SELECT id, name FROM categories;";
    private static final String SELECT_BY_ID_QUERY = "SELECT id, name FROM categories WHERE id = ?;";
    private static final String UPDATE_QUERY = """
            UPDATE categories
            SET name = ?,
            WHERE category_id = ?;
            """;
    private static final String DELETE_QUERY = """
            DELETE FROM categories
            WHERE id = ?;
            """;

    private final RowMapper<Category> categoryRowMapper = CategoryRowMapper.getInstance();

    @Override
    public void create(final Category category) {
        try (var connection = ConnectionPool.get();
             var prepareStatement = connection.prepareStatement(INSERT_QUERY)) {
            prepareStatement.setString(1, category.getName());
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ERROR_TEMPLATE.formatted(e.getMessage()));
        }
    }

    @Override
    public List<Category> findAll() {
        try (var connection = ConnectionPool.get();
             var prepareStatement = connection.prepareStatement(SELECT_ALL_QUERY);
             var resultSet = prepareStatement.executeQuery()
        ) {
            var categories = new ArrayList<Category>();
            while (resultSet.next()) {
                categories.add(this.categoryRowMapper.mapRow(resultSet));
            }
            return categories;
        } catch (SQLException e) {
            throw new DaoException(ERROR_TEMPLATE.formatted(e.getMessage()));
        }
    }

    @Override
    public void update(final Long id, final Category category) {
        try (var connection = ConnectionPool.get();
             var prepareStatement = connection.prepareStatement(UPDATE_QUERY)) {
            prepareStatement.setString(1, category.getName());
            prepareStatement.setLong(2, id);
            prepareStatement.executeUpdate();
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

    @Override
    public Optional<Category> findById(final Long id) {
        try (var connection = ConnectionPool.get();
             var prepareStatement = connection.prepareStatement(SELECT_BY_ID_QUERY);
        ) {
            Category category = null;
            connection.setAutoCommit(true);
            prepareStatement.setLong(1, id);
            try (var resultSet = prepareStatement.executeQuery()) {
                if (resultSet.next()) {
                    category = this.categoryRowMapper.mapRow(resultSet);
                }
            }
            return Optional.ofNullable(category);
        } catch (SQLException e) {
            throw new DaoException(ERROR_TEMPLATE.formatted(e.getMessage()));
        }
    }
}
