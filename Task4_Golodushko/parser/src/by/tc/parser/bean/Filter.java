package by.tc.parser.bean;

public class Filter {

    String filterName;
    String filterClass;
    InitParam initParam;

    public String getFilterName() {
        return filterName;
    }

    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }

    public String getFilterClass() {
        return filterClass;
    }

    public void setFilterClass(String filterClass) {
        this.filterClass = filterClass;
    }

    public InitParam getInitParam() {
        return initParam;
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

        Filter filter = (Filter) o;


        if (getFilterName()==null) {
            if (filter.getFilterName() != null) {
                return false;
            }
        } else if (!getFilterName().equals(filter.getFilterName())) {
            return false;
        }


        if (getFilterClass()==null) {
            if (filter.getFilterClass() != null) {
                return false;
            }
        } else if (!getFilterClass().equals(filter.getFilterClass())) {
            return false;
        }


        if (getInitParam()==null) {
            if (filter.getInitParam() != null) {
                return false;
            }
        } else if (!getInitParam().equals(filter.getInitParam())) {
            return false;
        }

        return true;

    }

    @Override
    public int hashCode() {
        int result = getFilterName().hashCode();
        result = 31 * result + getFilterClass().hashCode();
        result = 31 * result + getInitParam().hashCode();
        return result;
    }

    public void setInitParam(InitParam initParam) {
        this.initParam = initParam;
    }

    @Override
    public String toString() {
        return "Filter{" +
                "filterName='" + filterName + '\'' +
                ", filterClass='" + filterClass + '\'' +
                ", initParam=" + initParam +
                '}';
    }
}
