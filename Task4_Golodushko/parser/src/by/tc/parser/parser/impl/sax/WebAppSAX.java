package by.tc.parser.parser.impl.sax;

import by.tc.parser.bean.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class WebAppSAX extends DefaultHandler {
    private WebApp webApp;

    private ArrayList<WelcomeFileList> welcomeFileLists = new ArrayList<WelcomeFileList>();
    private ArrayList<Filter> filters = new ArrayList<Filter>();
    private ArrayList<FilterMapping> filterMappings = new ArrayList<FilterMapping>();
    private ArrayList<Listener> listeners = new ArrayList<Listener>();
    private ArrayList<Servlet> servlets = new ArrayList<Servlet>();
    private ArrayList<ServletMapping> servletMappings = new ArrayList<ServletMapping>();
    private ArrayList<ErrorPage> errorPages = new ArrayList<ErrorPage>();

    private WelcomeFileList welcomeFileList;
    private Filter filter;
    private FilterMapping filterMapping;
    private Listener listener;
    private Servlet servlet;
    private ServletMapping servletMapping;
    private ErrorPage errorPage;
    private InitParam initParam;

    private StringBuilder text;

    public WebApp getWebApp() {
        return webApp;
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        text = new StringBuilder();

        switch (localName ) {
            case "web-app":
                webApp = new WebApp();
                webApp.setIdAttr(attributes.getValue("wa:id"));
                webApp.setVersionAttr(attributes.getValue("wa:version"));
                break;
            case "welcome-file-list":
                welcomeFileList = new WelcomeFileList();
                break;
            case "filter":
                filter = new Filter();
                break;
            case "init-param":
                initParam = new InitParam();
                break;
            case "filter-mapping":
                filterMapping = new FilterMapping();
                break;
            case "listener":
                listener = new Listener();
                break;
            case "servlet":
                servlet = new Servlet();
                break;
            case "servlet-mapping":
                servletMapping = new ServletMapping();
                break;
            case "error-page":
                errorPage = new ErrorPage();
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (localName){
            case "display-name":
                webApp.addDisplayName(text.toString());
                break;
            case "welcome-file":
                welcomeFileList.addWelcomeFile(text.toString());
                break;
            case "welcome-file-list":
                welcomeFileLists.add(welcomeFileList);
                welcomeFileList = null;
                break;
            case "filter-name":
                if (filter!=null) {
                    filter.setFilterName(text.toString());
                } else {
                    filterMapping.setFilterName(text.toString());
                }
                break;
            case "filter-class":
                filter.setFilterClass(text.toString());
                break;
            case "param-name":
                initParam.setParamName(text.toString());
                break;
            case "param-value":
                initParam.setParamValue(text.toString());
                break;
            case "init-param":
                if (filter!=null) {
                    filter.setInitParam(initParam);
                } else {
                    servlet.setInitParam(initParam);
                }
                initParam = null;
                break;
            case "filter":
                filters.add(filter);
                filter = null;
                break;
            case "url-pattern":
                if (filterMapping!=null) {
                    filterMapping.setUrlPattern(text.toString());
                } else {
                    servletMapping.setUrlPattern(text.toString());
                }
                break;
            case "dispatcher":
                filterMapping.setDispatcher(text.toString());
                break;
            case "filter-mapping":
                filterMappings.add(filterMapping);
                filterMapping = null;
                break;
            case "listener-class":
                listener.setListenerClass(text.toString());
                break;
            case "listener":
                listeners.add(listener);
                break;
            case "servlet-name":
                if (servlet!=null) {
                    servlet.setServletName(text.toString());
                } else {
                    servletMapping.setServletName(text.toString());
                }
                break;
            case "servlet-class":
                servlet.setServletClass(text.toString());
                break;
            case "servlet":
                servlets.add(servlet);
                servlet = null;
                break;
            case "servlet-mapping":
                servletMappings.add(servletMapping);
                servletMapping = null;
                break;
            case "exception-type":
                errorPage.setExceptionType(text.toString());
                break;
            case "error-code":
                errorPage.setErrorCode(text.toString());
                break;
            case "location":
                errorPage.setLocation(text.toString());
                break;
            case "error-page":
                errorPages.add(errorPage);
                errorPage = null;
                break;
            case "web-app":
                webApp.setWelcomeFileLists(welcomeFileLists);
                webApp.setFilters(filters);
                webApp.setFilterMappings(filterMappings);
                webApp.setListeners(listeners);
                webApp.setServlets(servlets);
                webApp.setServletMappings(servletMappings);
                webApp.setErrorPages(errorPages);
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        text.append(ch, start, length);
    }

    @Override
    public void warning(SAXParseException e) throws SAXException {
       System.err.println("WARNING: line " + e.getLineNumber() + ": " + e.getMessage());
    }

    @Override
    public void error(SAXParseException e) throws SAXException {
        System.err.println("ERROR: line " + e.getLineNumber() + ": " + e.getMessage());
    }

    @Override
    public void fatalError(SAXParseException e) throws SAXException {
        System.err.println("FATAL: line " + e.getLineNumber() + ": " + e.getMessage());
    }
}
