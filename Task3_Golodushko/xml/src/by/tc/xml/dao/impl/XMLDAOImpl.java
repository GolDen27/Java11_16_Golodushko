package by.tc.xml.dao.impl;


import by.tc.xml.dao.XMLDAO;
import by.tc.xml.dao.exception.DAOException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class XMLDAOImpl implements XMLDAO {
    BufferedReader reader= null;
    String SYSTEM_SYMBOL = "\t\r\n";

    @Override
    public void setFile(String path) throws DAOException {
        try {
            reader = new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException e)
        {
            throw new DAOException("file not found", e.getCause());
        }
    }

    @Override
    public char getSymbol() throws DAOException {
        int symbol;
        try {
            symbol = reader.read();
        } catch (IOException e) {
            throw new DAOException("err read symbol", e.getCause());
        }
        if (SYSTEM_SYMBOL.indexOf(symbol)!=-1) {
            symbol = ' ';
        }
        return (char) symbol;
    }



    @Override
    public BufferedReader getReader() {
        return reader;
    }

    @Override
    public void closeReader() throws DAOException {
        try {
            reader.close();
        } catch (IOException e) {
            throw new DAOException(e.getMessage());
        }
    }
}
