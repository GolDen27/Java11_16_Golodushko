package by.tc.eq.dao.connection;

import by.tc.eq.dao.exception.DAOException;

import java.sql.Connection;

public interface ConnectionPool {
    Connection retrieve() throws DAOException;
    void putback(Connection c) throws DAOException;
    void closeAll() throws DAOException;
}
