package org.elyashevich.ecommerceapplication.dao.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerceapplication.dao.RoleDao;
import org.elyashevich.ecommerceapplication.dao.UserDao;
import org.elyashevich.ecommerceapplication.dao.mapper.RowMapper;
import org.elyashevich.ecommerceapplication.dao.mapper.impl.UserRowMapper;
import org.elyashevich.ecommerceapplication.db.ConnectionPool;
import org.elyashevich.ecommerceapplication.entity.Role;
import org.elyashevich.ecommerceapplication.entity.User;
import org.elyashevich.ecommerceapplication.exception.DaoException;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.elyashevich.ecommerceapplication.util.DaoErrorUtil.ERROR_TEMPLATE;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDaoImpl implements UserDao {

    @Getter
    private static final UserDaoImpl instance = new UserDaoImpl();

    private static final String INSERT_QUERY = """
            INSERT INTO users (username, email, password, full_name, address)
            VALUES (?, ?, ?, ?, ?);
            """;
    private static final String SET_ROLE_QUERY = """
            INSERT INTO user_roles (user_id, role_id)
            VALUES (?, ?);
            """;
    private static final String SELECT_ALL = """
            SELECT u.id as id, 
            u.username as username,
            u.email as email,
            u.password as password,
            u.full_name as full_name,
            u.address as address,
            r.name as role_name
            FROM users u
            JOIN user_roles ur ON u.id = ur.user_id
            JOIN roles r ON ur.role_id = r.id;
            """;
    private static final String SELECT_BY_EMAIL = """
                SELECT u.id as id, 
                u.username as username,
                u.email as email,
                u.password as password,
                u.full_name as full_name,
                u.address as address,
                r.name as role_name
                FROM users u
                JOIN user_roles ur ON u.id = ur.user_id
                JOIN roles r ON ur.role_id = r.id
                WHERE u.email = ?;
            """;
    private static final String UPDATE_QUERY = """
            UPDATE users
            SET username = ?, email = ?, password = ?, full_name = ?, address = ?
            WHERE id = ?;
            """;
    private static final String DELETE_QUERY = "DELETE FROM users WHERE id = ?;";
    private static final String SET_IMAGE_QUERY = """
            UPDATE users
            SET image = ?
            WHERE id = ?;
            """;

    private final RoleDao roleDao = RoleDaoImpl.getInstance();

    private final RowMapper<User> userRowMapper = UserRowMapper.getInstance();

    @Override
    public Long create(final User user) {
        try (var connection = ConnectionPool.get();
             var prepareStatement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            prepareStatement.setString(1, user.getUsername());
            prepareStatement.setString(2, user.getEmail());
            prepareStatement.setString(3, user.getPassword());
            prepareStatement.setString(4, user.getFullName());
            prepareStatement.setString(5, user.getAddress());
            var affectedRows = prepareStatement.executeUpdate();
            Long id = null;
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }
            try (var generatedKeys = prepareStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    id = generatedKeys.getLong(1);
                    this.defineRole(id, this.roleDao.findByName(user.getRole().getName()));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
            return id;
        } catch (SQLException e) {
            throw new DaoException(ERROR_TEMPLATE.formatted(e.getMessage()));
        }
    }

    @Override
    public List<User> findAll() {
        try (var connection = ConnectionPool.get();
             var prepareStatement = connection.prepareStatement(SELECT_ALL);
             var resultSet = prepareStatement.executeQuery()
        ) {
            var users = new ArrayList<User>();
            while (resultSet.next()) {
                users.add(this.userRowMapper.mapRow(resultSet));
            }
            return users;
        } catch (SQLException e) {
            throw new DaoException(ERROR_TEMPLATE.formatted(e.getMessage()));
        }
    }

    @Override
    public void update(final Long id, final User user) {
        try (var connection = ConnectionPool.get();
             var prepareStatement = connection.prepareStatement(UPDATE_QUERY)) {
            prepareStatement.setString(1, user.getUsername());
            prepareStatement.setString(2, user.getEmail());
            prepareStatement.setString(3, user.getPassword());
            prepareStatement.setString(4, user.getFullName());
            prepareStatement.setString(5, user.getAddress());
            prepareStatement.setLong(6, user.getId());
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
    public void defineRole(final Long id, final Role role) {
        try (var connection = ConnectionPool.get();
             var prepareStatement = connection.prepareStatement(SET_ROLE_QUERY)) {
            prepareStatement.setLong(1, id);
            prepareStatement.setLong(2, role.getId());
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ERROR_TEMPLATE.formatted(e.getMessage()));
        }
    }

    @Override
    public Optional<User> findByEmail(final String email) {
        try (var connection = ConnectionPool.get();
             var prepareStatement = connection.prepareStatement(SELECT_BY_EMAIL)) {
            prepareStatement.setString(1, email);
            User user = null;
            try (var resultSet = prepareStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = this.userRowMapper.mapRow(resultSet);
                }
            }
            return Optional.ofNullable(user);
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
