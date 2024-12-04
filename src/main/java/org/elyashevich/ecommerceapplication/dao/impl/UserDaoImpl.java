package org.elyashevich.ecommerceapplication.dao.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elyashevich.ecommerceapplication.dao.RoleDao;
import org.elyashevich.ecommerceapplication.dao.UserDao;
import org.elyashevich.ecommerceapplication.db.ConnectionPool;
import org.elyashevich.ecommerceapplication.entity.Role;
import org.elyashevich.ecommerceapplication.entity.User;
import org.elyashevich.ecommerceapplication.exception.DaoException;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// TODO: implements roles logic
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDaoImpl implements UserDao {

    private static final String INSERT_QUERY = """
            INSERT INTO users (username, email, password, full_name, address)
            VALUES (?, ?, ?, ?, ?);
            """;
    private static final String SET_ROLE_QUERY = """
            INSERT INTO user_roles (user_id, role_id)
            VALUES (?, ?);
            """;
    private static final String SELECT_ALL = """
            SELECT u.id, u.username, u.email, u.password, u.full_name, u.address, r.name
            FROM users u
            JOIN user_roles ur ON u.id = ur.user_id
            JOIN roles r ON ur.role_id = r.id;
            """;
    private static final String SELECT_BY_EMAIL = """
                SELECT u.id, u.username, u.email, u.password, u.full_name, u.address, r.name
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
    private static final String ERROR_TEMPLATE = "Transaction declined: %s";

    @Getter
    private static final UserDaoImpl instance = new UserDaoImpl();

    private final RoleDao roleDao = RoleDaoImpl.getInstance();

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
                    this.defineRole(id, this.roleDao.findByName("USER"));
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
                var user = User.builder()
                        .id(resultSet.getLong(1))
                        .username(resultSet.getString(2))
                        .email(resultSet.getString(3))
                        .fullName(resultSet.getString(4))
                        .address(resultSet.getString(5))
                        .build();
                users.add(user);
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

    // TODO: handle list of roles
    @Override
    public Optional<User> findByEmail(final String email) {
        try (var connection = ConnectionPool.get();
             var prepareStatement = connection.prepareStatement(SELECT_BY_EMAIL)) {
            prepareStatement.setString(1, email);
            User user = null;
            try (var resultSet = prepareStatement.executeQuery()) {
                if (resultSet.next()) {
                    var role = Role.builder()
                            .name(resultSet.getString(7))
                            .build();
                    user = User.builder()
                            .id(resultSet.getLong(1))
                            .username(resultSet.getString(2))
                            .email(resultSet.getString(3))
                            .password(resultSet.getString(4))
                            .fullName(resultSet.getString(5))
                            .address(resultSet.getString(6))
                            .roles(List.of(role))
                            .build();
                }
            }
            return Optional.ofNullable(user);
        } catch (SQLException e) {
            throw new DaoException(ERROR_TEMPLATE.formatted(e.getMessage()));
        }
    }
}
