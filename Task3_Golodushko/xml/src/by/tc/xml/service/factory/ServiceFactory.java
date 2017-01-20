package by.tc.xml.service.factory;

import by.tc.xml.service.Impl.XMLServiceImpl;
import by.tc.xml.service.XMLService;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private final XMLService xmlService = new XMLServiceImpl();

    private ServiceFactory () {}

    public static ServiceFactory getInstance(){
        return instance;
    }

    public XMLService getxmlService() {
        return xmlService;
    }
}
