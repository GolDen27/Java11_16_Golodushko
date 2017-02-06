package by.tc.parser.bean;

import java.util.ArrayList;

public class WebApp {

    ArrayList <WelcomeFileList> welcomeFileLists;
    ArrayList <Filter> filters;
    ArrayList <FilterMapping> filterMappings;
    ArrayList <Listener> listeners;
    ArrayList <Servlet> servlets;
    ArrayList <ServletMapping> servletMappings;
    ArrayList <ErrorPage> errorPages;


    ArrayList <String> displayNames = new ArrayList<String>();

    String idAttr;
    String versionAttr;

    public String getVersionAttr() {
        return versionAttr;
    }

    public void setVersionAttr(String versionAttr) {
        this.versionAttr = versionAttr;
    }

    public ArrayList<FilterMapping> getFilterMappings() {
        return filterMappings;
    }

    public void setFilterMappings(ArrayList<FilterMapping> filterMappings) {
        this.filterMappings = filterMappings;
    }

    public ArrayList<Listener> getListeners() {
        return listeners;
    }

    public void setListeners(ArrayList<Listener> listeners) {
        this.listeners = listeners;
    }

    public ArrayList<Servlet> getServlets() {
        return servlets;
    }

    public void setServlets(ArrayList<Servlet> servlets) {
        this.servlets = servlets;
    }

    public ArrayList<ServletMapping> getServletMappings() {
        return servletMappings;
    }

    public void setServletMappings(ArrayList<ServletMapping> servletMappings) {
        this.servletMappings = servletMappings;
    }

    public ArrayList<ErrorPage> getErrorPages() {
        return errorPages;
    }

    public void setErrorPages(ArrayList<ErrorPage> errorPages) {
        this.errorPages = errorPages;
    }

    public String getIdAttr() {
        return idAttr;
    }

    public void setIdAttr(String idAttr) {
        this.idAttr = idAttr;
    }

    public ArrayList<Filter> getFilters() {
        return filters;
    }

    public void setFilters(ArrayList<Filter> filters) {
        this.filters = filters;
    }

    public ArrayList<WelcomeFileList> getWelcomeFileLists() {
        return welcomeFileLists;
    }

    public void setWelcomeFileLists(ArrayList<WelcomeFileList> welcomeFileLists) {
        this.welcomeFileLists = welcomeFileLists;
    }

    public ArrayList<String> getDisplayNames() {
        return displayNames;
    }

    public void setDisplayNames(ArrayList<String> displayNames) {
        this.displayNames = displayNames;
    }

    public void addDisplayName(String displayName) {
        displayNames.add(displayName);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }

        if (this.getClass() != o.getClass()) {
            return false;
        }

        WebApp webApp = (WebApp) o;


        if (getWelcomeFileLists()==null) {
            if (webApp.getWelcomeFileLists() != null) {
                return false;
            }
        } else if (!getWelcomeFileLists().equals(webApp.getWelcomeFileLists())) {
            return false;
        }


        if (getFilters()==null) {
            if (webApp.getFilters() != null) {
                return false;
            }
        } else if (!getFilters().equals(webApp.getFilters())) {
            return false;
        }


        if (getFilterMappings()==null) {
            if (webApp.getFilterMappings() != null) {
                return false;
            }
        } else if (!getFilterMappings().equals(webApp.getFilterMappings())) {
            return false;
        }


        if (getListeners()==null) {
            if (webApp.getListeners() != null) {
                return false;
            }
        } else if (!getListeners().equals(webApp.getListeners())) {
            return false;
        }


        if (getServlets()==null) {
            if (webApp.getServlets() != null) {
                return false;
            }
        } else if (!getServlets().equals(webApp.getServlets())) {
            return false;
        }


        if (getServletMappings()==null) {
            if (webApp.getServletMappings() != null) {
                return false;
            }
        } else if (!getServletMappings().equals(webApp.getServletMappings())) {
            return false;
        }


        if (getErrorPages()==null) {
            if (webApp.getErrorPages() != null) {
                return false;
            }
        } else if (!getErrorPages().equals(webApp.getErrorPages())) {
            return false;
        }


        if (getDisplayNames()==null) {
            if (webApp.getDisplayNames() != null) {
                return false;
            }
        } else if (!getDisplayNames().equals(webApp.getDisplayNames())) {
            return false;
        }


        if (getIdAttr()==null) {
            if (webApp.getIdAttr() != null) {
                return false;
            }
        } else if (!getIdAttr().equals(webApp.getIdAttr())) {
            return false;
        }


        if (getVersionAttr()==null) {
            if (webApp.getVersionAttr() != null) {
                return false;
            }
        } else if (!getVersionAttr().equals(webApp.getVersionAttr())) {
            return false;
        }

        return true;

    }

    @Override
    public int hashCode() {
        int result = getWelcomeFileLists().hashCode();
        result = 31 * result + getFilters().hashCode();
        result = 31 * result + getFilterMappings().hashCode();
        result = 31 * result + getListeners().hashCode();
        result = 31 * result + getServlets().hashCode();
        result = 31 * result + getServletMappings().hashCode();
        result = 31 * result + getErrorPages().hashCode();
        result = 31 * result + getDisplayNames().hashCode();
        result = 31 * result + getIdAttr().hashCode();
        result = 31 * result + getVersionAttr().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "WebApp{" +
                "welcomeFileLists=" + welcomeFileLists +
                ", filters=" + filters +
                ", filterMappings=" + filterMappings +
                ", listeners=" + listeners +
                ", servlets=" + servlets +
                ", servletMappings=" + servletMappings +
                ", errorPages=" + errorPages +
                ", displayNames=" + displayNames +
                ", idAttr='" + idAttr + '\'' +
                ", versionAttr='" + versionAttr + '\'' +
                '}';
    }
}
