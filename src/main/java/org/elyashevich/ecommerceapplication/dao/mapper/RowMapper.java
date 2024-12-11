package org.elyashevich.ecommerceapplication.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T> {

    T mapRow(final ResultSet rs) throws SQLException;
}
