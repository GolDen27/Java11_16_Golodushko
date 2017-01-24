package by.tc.xml.dao;

import by.tc.xml.dao.exception.DAOException;

import java.io.BufferedReader;

public interface XMLDAO {
    void setFile (String path) throws DAOException;
    char getSymbol () throws DAOException;
    BufferedReader getReader ();
    void closeReader() throws DAOException;
}
