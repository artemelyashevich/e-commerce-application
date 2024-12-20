package org.elyashevich.ecommerceapplication.db;

import org.elyashevich.ecommerceapplication.util.PropertyUtil;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static org.elyashevich.ecommerceapplication.util.DbUtil.*;

public final class ConnectionPool {

    private static final int DEFAULT_POOL_SIZE = 10;

    private static BlockingQueue<Connection> pool;

    static {
        initConnection();
    }

    private ConnectionPool() {
    }

    public static Connection get() {
        try {
            return pool.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static Connection open() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(
                    PropertyUtil.loadProperty(DB_PROPERTIES_FILENAME, URL_KEY),
                    PropertyUtil.loadProperty(DB_PROPERTIES_FILENAME, USERNAME_KEY),
                    PropertyUtil.loadProperty(DB_PROPERTIES_FILENAME, PASSWORD_KEY)
                    );
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void initConnection() {
        var size = DEFAULT_POOL_SIZE;
        pool = new ArrayBlockingQueue<>(size);
        for (int i = 0; i < size; i++) {
            var connection = open();
            var proxyConnection = (Connection) Proxy.newProxyInstance(
                    Connection.class.getClassLoader(),
                    new Class[]{Connection.class},
                    (proxy, method, args) -> {
                        if (method.getName().equals("close")) {
                            pool.add((Connection) proxy);
                            return null;
                        } else {
                            return method.invoke(connection, args);
                        }
                    }
            );
            pool.add(proxyConnection);
        }
    }
}
