package by.tc.parser.parser.impl.dom;

import by.tc.parser.bean.*;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

public class WebAppDOM {
    DOMParser parser;

    public WebAppDOM(DOMParser parser) {
        this.parser = parser;
    }

    public WebApp parse() {
        WebApp webApp = new WebApp();

        Document document = parser.getDocument();
        Element root = document.getDocumentElement();

        webApp.setVersionAttr(root.getAttribute("wa:version"));
        webApp.setIdAttr(root.getAttribute("wa:id"));
        webApp.setDisplayNames(getDisplayNames(root));
        webApp.setWelcomeFileLists(getWelcomeFileLists(root));
        webApp.setFilters(getFilters(root));
        webApp.setFilterMappings(getFilterMappings(root));
        webApp.setListeners(getListeners(root));
        webApp.setServlets(getServlets(root));
        webApp.setServletMappings(getServletMappings(root));
        webApp.setErrorPages(getErrorPages(root));

        return webApp;
    }

    private ArrayList<String> getDisplayNames (Element root) {
        ArrayList<String> displayNames = new ArrayList<String>();
        NodeList displayNameNodes = root.getElementsByTagName("wa:display-name");
        String displayName = null;
        for (int i = 0; i < displayNameNodes.getLength(); i++) {
            Element displayNameElement = (Element) displayNameNodes.item(i);
            displayName = displayNameElement.getTextContent().trim();
            displayNames.add(displayName);
        }
        return displayNames;
    }

    private ArrayList<WelcomeFileList> getWelcomeFileLists (Element root) {
        ArrayList<WelcomeFileList> welcomeFileLists = new ArrayList<WelcomeFileList>();
        NodeList welcomeFileListNodes = root.getElementsByTagName("wa:welcome-file-list");
        WelcomeFileList welcomeFileList;
        for (int i = 0; i < welcomeFileListNodes.getLength(); i++) {
            welcomeFileList = new WelcomeFileList();
            Element welcomeFileListElement = (Element) welcomeFileListNodes.item(i);
            NodeList welcomeFileNodes = welcomeFileListElement.getElementsByTagName("wa:welcome-file");
            for (int j = 0; j < welcomeFileNodes.getLength(); j++) {
                Element welcomeFileElement = (Element) welcomeFileNodes.item(j);
                welcomeFileList.addWelcomeFile(welcomeFileElement.getTextContent().trim());
            }
            welcomeFileLists.add(welcomeFileList);
        }
        return welcomeFileLists;
    }

    private ArrayList<Filter> getFilters (Element root) {
        ArrayList<Filter> filters = new ArrayList<Filter>();
        NodeList filterNodes = root.getElementsByTagName("wa:filter");
        Filter filter;
        for (int i = 0; i < filterNodes.getLength(); i++) {
            filter = new Filter();
            Element filterElement = (Element) filterNodes.item(i);
            filter.setFilterName(getSingleChildElement(filterElement, "wa:filter-name").getTextContent().trim());
            filter.setFilterClass(getSingleChildElement(filterElement, "wa:filter-class").getTextContent().trim());
            filter.setInitParam(getInitParaams(filterElement));
            filters.add(filter);
        }
        return filters;
    }

    private ArrayList<FilterMapping> getFilterMappings (Element root) {
        ArrayList<FilterMapping> filterMappings = new ArrayList<FilterMapping>();
        FilterMapping filterMapping = null;
        NodeList filterMappingNodes = root.getElementsByTagName("wa:filter-mapping");
        for (int i = 0; i < filterMappingNodes.getLength(); i++) {
            filterMapping = new FilterMapping();
            Element filterMappingElement = (Element) filterMappingNodes.item(i);
            filterMapping.setFilterName(getSingleChildElement(filterMappingElement,"wa:filter-name").getTextContent().trim());
            filterMapping.setUrlPattern(getSingleChildElement(filterMappingElement,"wa:url-pattern").getTextContent().trim());
            filterMapping.setDispatcher(getSingleChildElement(filterMappingElement,"wa:dispatcher").getTextContent().trim());
            filterMappings.add(filterMapping);
        }
        return filterMappings;
    }

    private ArrayList<Listener> getListeners (Element root) {
        ArrayList<Listener> listeners = new ArrayList<Listener>();
        Listener listener = null;
        NodeList listenerNodes = root.getElementsByTagName("wa:listener");
        for (int i = 0; i < listenerNodes.getLength(); i++) {
            listener = new Listener();
            Element listenerElement = (Element) listenerNodes.item(i);
            listener.setListenerClass(getSingleChildElement(listenerElement,"wa:listener-class").getTextContent().trim());
            listeners.add(listener);
        }
        return listeners;
    }

    private ArrayList<Servlet> getServlets (Element root) {
        ArrayList<Servlet> servlets = new ArrayList<Servlet>();
        Servlet servlet = null;
        NodeList servletNodes = root.getElementsByTagName("wa:servlet");
        for (int i = 0; i < servletNodes.getLength(); i++) {
            servlet = new Servlet();
            Element servletElement = (Element) servletNodes.item(i);
            servlet.setServletName(getSingleChildElement(servletElement, "wa:servlet-name").getTextContent().trim());
            servlet.setServletClass(getSingleChildElement(servletElement, "wa:servlet-class").getTextContent().trim());
            servlet.setInitParam(getInitParaams(servletElement));
            servlets.add(servlet);
        }
        return servlets;
    }

    private ArrayList<ServletMapping> getServletMappings (Element root) {
        ArrayList<ServletMapping> servletMappings = new ArrayList<ServletMapping>();
        ServletMapping servletMapping = null;
        NodeList servletMappingNodes = root.getElementsByTagName("wa:servlet-mapping");
        for (int i = 0; i < servletMappingNodes.getLength(); i++) {
            servletMapping = new ServletMapping();
            Element servletMappingElement = (Element) servletMappingNodes.item(i);
            servletMapping.setServletName(getSingleChildElement(servletMappingElement,"wa:servlet-name").getTextContent().trim());
            servletMapping.setUrlPattern(getSingleChildElement(servletMappingElement,"wa:url-pattern").getTextContent().trim());
            servletMappings.add(servletMapping);
        }
        return servletMappings;
    }

    private ArrayList<ErrorPage> getErrorPages (Element root) {
        ArrayList<ErrorPage> errorPages = new ArrayList<ErrorPage>();
        ErrorPage errorPage = null;
        NodeList errorPageNodes = root.getElementsByTagName("wa:error-page");
        for (int i = 0; i < errorPageNodes.getLength(); i++) {
            errorPage = new ErrorPage();
            Element errorPageElement = (Element) errorPageNodes.item(i);
            try {
                errorPage.setExceptionType(getSingleChildElement(errorPageElement,"wa:exception-type").getTextContent().trim());
            } catch (NullPointerException e) {}
            try {
                errorPage.setErrorCode(getSingleChildElement(errorPageElement,"wa:error-code").getTextContent().trim());
            } catch (NullPointerException e) {}
            errorPage.setLocation(getSingleChildElement(errorPageElement, "wa:location").getTextContent().trim());
            errorPages.add(errorPage);
        }
        return errorPages;
    }

    private InitParam getInitParaams (Element root) {
        InitParam initParam = new InitParam();
        Element initParamsElement = getSingleChildElement(root,"wa:init-param");
        if (initParamsElement != null ) {
            initParam.setParamName(getSingleChildElement(initParamsElement, "wa:param-name").getTextContent().trim());
            initParam.setParamValue(getSingleChildElement(initParamsElement, "wa:param-value").getTextContent().trim());
        }
        if ((initParam.getParamName()==null)||(initParam.getParamValue()==null)) {
            initParam = null;
        }
        return initParam;
    }

    private Element getSingleChildElement (Element root, String childName) {
        NodeList nodeList = root.getElementsByTagName(childName);
        return nodeList.getLength()>0?(Element)nodeList.item(0):null;
    }

}
