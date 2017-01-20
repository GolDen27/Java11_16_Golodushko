package by.tc.xml.dao.factory;

import by.tc.xml.dao.XMLDAO;
import by.tc.xml.dao.impl.XMLDAOImpl;

public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private final XMLDAO xmldao = new XMLDAOImpl();

    private DAOFactory () {}

    public static DAOFactory getInstance(){
        return instance;
    }

    public XMLDAO getXmldao() {
        return xmldao;
    }
}
