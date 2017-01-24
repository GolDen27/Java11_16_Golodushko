package by.tc.xml.service;

import by.tc.xml.service.exception.ServiceExceptinon;

public interface XMLService {
    void setFile (String path) throws ServiceExceptinon;
    String getNext () throws ServiceExceptinon;
    void closeReader() throws ServiceExceptinon;
}
