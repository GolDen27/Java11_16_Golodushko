package by.tc.parser.bean;

public class Servlet {

    String servletName;
    String servletClass;
    InitParam initParam;

    public InitParam getInitParam() {
        return initParam;
    }

    public void setInitParam(InitParam initParam) {
        this.initParam = initParam;
    }

    public String getServletName() {
        return servletName;
    }

    public void setServletName(String servletName) {
        this.servletName = servletName;
    }


    public String getServletClass() {
        return servletClass;
    }

    public void setServletClass(String servletClass) {
        this.servletClass = servletClass;
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

        Servlet servlet = (Servlet) o;


        if (getInitParam()==null) {
            if (servlet.getInitParam() != null) {
                return false;
            }
        } else if (!getInitParam().equals(servlet.getInitParam())) {
            return false;
        }


        if (getServletClass()==null) {
            if (servlet.getServletClass() != null) {
                return false;
            }
        } else if (!getServletClass().equals(servlet.getServletClass())) {
            return false;
        }


        if (getServletName()==null) {
            if (servlet.getServletName() != null) {
                return false;
            }
        } else if (!getServletName().equals(servlet.getServletName())) {
            return false;
        }

        return true;

    }

    @Override
    public int hashCode() {
        int result = getServletName().hashCode();
        result = 31 * result + getServletClass().hashCode();
        result = 31 * result + getInitParam().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Servlet{" +
                "servletName='" + servletName + '\'' +
                ", servletClass='" + servletClass + '\'' +
                ", initParam=" + initParam +
                '}';
    }
}
