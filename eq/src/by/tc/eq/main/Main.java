package by.tc.eq.main;

import by.tc.eq.dao.connection.ConnectionFactory;
import by.tc.eq.dao.exception.DAOException;
import by.tc.eq.service.exception.ServiceException;

public class Main {
    public static void main(String[] args) throws DAOException, ServiceException {



        ConnectionFactory.getInstance().getConnectionPool().closeAll();
    }
}
