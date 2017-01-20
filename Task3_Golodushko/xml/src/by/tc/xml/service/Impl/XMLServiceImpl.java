package by.tc.xml.service.Impl;

import by.tc.xml.dao.exception.DAOException;
import by.tc.xml.dao.factory.DAOFactory;
import by.tc.xml.service.XMLService;
import by.tc.xml.service.exception.ServiceExceptinon;

import java.io.IOException;

public class XMLServiceImpl implements XMLService {
    @Override
    public void setFile(String path) throws ServiceExceptinon {
        try {
            DAOFactory.getInstance().getXmldao().setFile(path);
        } catch (DAOException e) {
            throw new ServiceExceptinon(e.getMessage(), e.getCause());
        }
    }


    @Override
    public String getNext() throws ServiceExceptinon {
        String result = "";

        char symbol = 0;
        boolean isStartTag, isEndTag;
        while (symbol != (char)-1) {
            try {
                symbol = DAOFactory.getInstance().getXmldao().getSymbol();
            } catch (DAOException e) {
                throw new ServiceExceptinon("err read", e.getCause());
            }

            if (symbol == '<') {

                try {
                    symbol = DAOFactory.getInstance().getXmldao().getSymbol();
                } catch (DAOException e) {
                    throw new ServiceExceptinon("err read", e.getCause());
                }

                if (symbol == '/') {
                    result = "close tag ";
                    while (symbol != '>') {
                        try {
                            symbol = DAOFactory.getInstance().getXmldao().getSymbol();
                        } catch (DAOException e) {
                            throw new ServiceExceptinon("err read", e.getCause());
                        }
                        result = result + String.valueOf(symbol);
                    }
                    result = result.substring(0,result.length()-1);
                    return result;
                } else {
                    result = "open tag " + String.valueOf(symbol);

                    String testString = "";
                    while (symbol != '>') {

                        try {
                            symbol = DAOFactory.getInstance().getXmldao().getSymbol();
                        } catch (DAOException e) {
                            throw new ServiceExceptinon("err read", e.getCause());
                        }
                        testString = testString + symbol;
                    }
                    testString = testString.substring(0,testString.length()-1);

                    String[] workString = testString.split(" ");

                    if (workString.length>1){
                        result=result+workString[0];
                        workString[0] = "";
                        result = result + " attr" + String.join(" ",workString);
                    } else {
                        result=result+workString[0];
                    }

                    String testStringBody = "";
                    while (symbol != '<') {
                        try {
                            DAOFactory.getInstance().getXmldao().getReader().mark(10);
                            symbol = DAOFactory.getInstance().getXmldao().getSymbol();
                            testStringBody = testStringBody + String.valueOf(symbol);
                        } catch (DAOException | IOException e) {
                            throw new ServiceExceptinon("err read", e.getCause());
                        }
                    }
                    testStringBody=testStringBody.substring(0,testStringBody.length()-1);
                    try {
                        DAOFactory.getInstance().getXmldao().getReader().reset();
                    } catch (IOException e) {
                        throw new ServiceExceptinon("err read", e.getCause());
                    }

                    if (testStringBody.split(" ").length > 0) {
                        result = result + " body " + testStringBody;
                        return result;
                    } else {
                        return result;
                    }

                }
            }
        }
        result = "end of file";
        return result;
    }



}
