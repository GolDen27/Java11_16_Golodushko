package by.tc.eq.dao.connection;

import by.tc.eq.dao.exception.DAOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Vector;

public class ConnectionPoolImpl implements ConnectionPool {

    private Vector<Connection> availableConns = new Vector<Connection>();
    private Vector<Connection> usedConns = new Vector<Connection>();
    private String url;
    private Properties properties = new Properties();


    public ConnectionPoolImpl(String url, String user, String password){
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        }
        catch (SQLException e) {
            e.printStackTrace();// похоже исключения - это твоя беда
        }
        this.url = url;
        this.properties.put("user",user);
        this.properties.put("password", password);
        this.properties.put("autoReconnect", "true");

    }

    private  Connection getConnection() throws DAOException {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url,properties);
        } catch (Exception e) {// почему перехватывается Exception?
            // мы же разбирали, в каких случаях его можно перезватывать
            throw new DAOException(e.getMessage());// вот и потерял реальное исключение
            // понимай, зачем на слое разрабатываются собственные исключения
            // подсказка - не для того, чтобы в логе не было никакой информации о первоначальной причине исключения
        }
        return conn;
    }

    @Override
    public synchronized  Connection retrieve() throws DAOException {
        Connection newConn = null;
        if (availableConns.size() == 0) {
            newConn = getConnection();
        } else {
            newConn = (Connection) availableConns.lastElement();
            availableConns.removeElement(newConn);
        }
        usedConns.addElement(newConn);
        return newConn;
    }

    @Override
    public synchronized void putback(Connection c) throws DAOException {
        if (c != null) {
            if (usedConns.removeElement(c)) {
                availableConns.addElement(c);
            } else {
                throw new DAOException("Connection not in the usedConns array");
            }
        }
    }

    @Override
    public void closeAll() throws DAOException {// а почему метод не синхронизированный?
        for (Connection cn : availableConns){
            try {
                cn.close();
            } catch (SQLException e) {
                throw new DAOException(e.getMessage());
            }
        }
        for (Connection cn : usedConns){
            try {
                cn.close();
            } catch (SQLException e) {
                throw new DAOException(e.getMessage());
            }
        }
    }
}
