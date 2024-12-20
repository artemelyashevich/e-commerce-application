package org.elyashevich.ecommerceapplication.dao.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerceapplication.dao.RoleDao;
import org.elyashevich.ecommerceapplication.dao.mapper.RowMapper;
import org.elyashevich.ecommerceapplication.dao.mapper.impl.RoleRowMapper;
import org.elyashevich.ecommerceapplication.db.ConnectionPool;
import org.elyashevich.ecommerceapplication.entity.Role;
import org.elyashevich.ecommerceapplication.exception.DaoException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.elyashevich.ecommerceapplication.util.DaoErrorUtil.ERROR_TEMPLATE;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RoleDaoImpl implements RoleDao {

    @Getter
    private static final RoleDaoImpl instance = new RoleDaoImpl();

    private static final String SELECT_ALL_QUERY = "SELECT id, name FROM roles;";
    private static final String INSERT_QUERY = """
                INSERT INTO roles (name) VALUES(?);
            """;
    private static final String UPDATE_QUERY = """
                UPDATE roles SET name = ? WHERE id = ?;
            """;
    private static final String DELETE_QUERY = """
                DELETE FROM roles WHERE id = ?;
            """;
    private static final String SELECT_BY_NAME = """
                SELECT id, name FROM roles WHERE name = ?;
            """;

    private final RowMapper<Role> roleRowMapper = RoleRowMapper.getInstance();

    @Override
    public void create(final Role role) {
        try (var connection = ConnectionPool.get();
             var prepareStatement = connection.prepareStatement(INSERT_QUERY)) {
            prepareStatement.setString(1, role.getName());
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ERROR_TEMPLATE.formatted(e.getMessage()));
        }
    }

    @Override
    public List<Role> findAll() {
        try (var connection = ConnectionPool.get();
             var prepareStatement = connection.prepareStatement(SELECT_ALL_QUERY);
             var resultSet = prepareStatement.executeQuery()) {
            var roles = new ArrayList<Role>();
            while (resultSet.next()) {
                roles.add(this.roleRowMapper.mapRow(resultSet));
            }
            return roles;
        } catch (SQLException e) {
            throw new DaoException(ERROR_TEMPLATE.formatted(e.getMessage()));
        }
    }

    @Override
    public void update(final Long id, final Role role) {
        try (var connection = ConnectionPool.get();
             var prepareStatement = connection.prepareStatement(UPDATE_QUERY)) {
            prepareStatement.setString(1, role.getName());
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
    public Role findByName(final String name) {
        try (var connection = ConnectionPool.get();
             var prepareStatement = connection.prepareStatement(SELECT_BY_NAME)) {
            prepareStatement.setString(1, name);
            Role role = null;
            try (var resultSet = prepareStatement.executeQuery()){
                if (resultSet.next()) {
                    role = this.roleRowMapper.mapRow(resultSet);
                }
            }
            return role;
        } catch (SQLException e) {
            throw new DaoException(ERROR_TEMPLATE.formatted(e.getMessage()));
        }
    }
}
