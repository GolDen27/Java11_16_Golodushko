package by.tc.parser.bean;

public class FilterMapping {

    String filterName;
    String urlPattern;
    String dispatcher;

    public String getFilterName() {
        return filterName;
    }

    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }

    public String getUrlPattern() {
        return urlPattern;
    }

    public void setUrlPattern(String urlPattern) {
        this.urlPattern = urlPattern;
    }

    public String getDispatcher() {
        return dispatcher;
    }

    public void setDispatcher(String dispatcher) {
        this.dispatcher = dispatcher;
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

        FilterMapping that = (FilterMapping) o;


        if (getFilterName()==null) {
            if (that.getFilterName() != null) {
                return false;
            }
        } else if (!getFilterName().equals(that.getFilterName())) {
            return false;
        }


        if (getUrlPattern()==null) {
            if (that.getUrlPattern() != null) {
                return false;
            }
        } else if (!getUrlPattern().equals(that.getUrlPattern())) {
            return false;
        }


        if (getDispatcher()==null) {
            if (that.getDispatcher() != null) {
                return false;
            }
        } else if (!getDispatcher().equals(that.getDispatcher())) {
            return false;
        }

        return true;

    }

    @Override
    public int hashCode() {
        int result = getFilterName().hashCode();
        result = 31 * result + getUrlPattern().hashCode();
        result = 31 * result + getDispatcher().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "FilterMapping{" +
                "filterName='" + filterName + '\'' +
                ", urlPattern='" + urlPattern + '\'' +
                ", dispatcher='" + dispatcher + '\'' +
                '}';
    }
}
