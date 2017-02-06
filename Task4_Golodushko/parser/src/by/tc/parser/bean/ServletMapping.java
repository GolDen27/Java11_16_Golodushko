package by.tc.parser.bean;

public class ServletMapping {

    String servletName;
    String urlPattern;


    public String getServletName() {

        return servletName;
    }

    public void setServletName(String servletName) {
        this.servletName = servletName;
    }

    public String getUrlPattern() {
        return urlPattern;
    }

    public void setUrlPattern(String urlPattern) {
        this.urlPattern = urlPattern;
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

        ServletMapping that = (ServletMapping) o;


        if (getServletName()==null) {
            if (that.getServletName() != null) {
                return false;
            }
        } else if (!getServletName().equals(that.getServletName())) {
            return false;
        }


        if (getUrlPattern()==null) {
            if (that.getUrlPattern() != null) {
                return false;
            }
        } else if (!getUrlPattern().equals(that.getUrlPattern())) {
            return false;
        }

        return true;

    }

    @Override
    public int hashCode() {
        int result = getServletName().hashCode();
        result = 31 * result + getUrlPattern().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ServletMapping{" +
                "servletName='" + servletName + '\'' +
                ", urlPattern='" + urlPattern + '\'' +
                '}';
    }
}
