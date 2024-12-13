package org.elyashevich.ecommerceapplication.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.elyashevich.ecommerceapplication.db.ConnectionPool;
import org.elyashevich.ecommerceapplication.exception.DaoException;

import java.sql.Connection;
import java.sql.SQLException;

@Aspect
public class TransactionAspect {

    @Pointcut("@annotation(Transactional)")
    public void transactionalMethods() {
    }

    @Around("transactionalMethods()")
    public Object manageTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        Connection connection = null;
        try {
            connection = ConnectionPool.get();
            connection.setAutoCommit(false);
            var result = joinPoint.proceed();
            connection.commit();
            return result;
        } catch (Exception e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException rollbackEx) {
                    throw new DaoException("Rollback failed: " + rollbackEx.getMessage());
                }
            }
            throw new RuntimeException("Transaction failed: " + e.getMessage(), e);
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException ex) {
                    throw new DaoException("Closing connection failed: " + ex.getMessage());
                }
            }
        }
    }
}
