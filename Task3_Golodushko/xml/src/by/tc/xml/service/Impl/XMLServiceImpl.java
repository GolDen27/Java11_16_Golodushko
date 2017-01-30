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
                result = openQuote(symbol);
                break;
            }
        }
        if (result=="") {
            result = "end of file";
        }
        return result;
    }

    @Override
    public void closeReader() throws ServiceExceptinon {
        try {
            DAOFactory.getInstance().getXmldao().closeReader();
        } catch (DAOException e) {
            throw new ServiceExceptinon(e.getMessage());
        }
    }

    private String openQuote (char workSymbol) throws ServiceExceptinon {
        char symbol = workSymbol;
        String result = "";
        try {
            symbol = DAOFactory.getInstance().getXmldao().getSymbol();
        } catch (DAOException e) {
            throw new ServiceExceptinon("err read", e.getCause());
        }

        if (symbol == '/') {
            return result = closeTag(symbol);
        } else {
            return result = openTag(symbol);
        }
    }

    private String closeTag (char workSymbol) throws ServiceExceptinon {
        String result = "";
        char symbol = workSymbol;
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
    }

    private String openTag (char workSymbol) throws ServiceExceptinon {
        String result = "";
        char symbol = workSymbol;
        result = "open tag " + String.valueOf(symbol);

        String workString = "";
        while (symbol != '>') {

            try {
                symbol = DAOFactory.getInstance().getXmldao().getSymbol();
            } catch (DAOException e) {
                throw new ServiceExceptinon("err read", e.getCause());
            }
            workString = workString + symbol;
        }
        workString = workString.substring(0, workString.length()-1);

        String[] workStrings = workString.split(" ");

        if (workStrings.length>1){
            result=result+ workStrings[0];
            workStrings[0] = "";
            result = result + " attr" + String.join(" ", workStrings);
        } else {
            result=result+ workStrings[0];
        }

        String testStringBody = getBody(symbol);

        if (testStringBody.split(" ").length > 0) {
            result = result + " body " + testStringBody;
            return result;
        } else {
            return result;
        }
    }

    private String getBody (char workSymbol) throws ServiceExceptinon {
        char symbol = workSymbol;
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
        return testStringBody;
    }


}
