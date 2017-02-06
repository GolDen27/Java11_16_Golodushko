package by.tc.parser.bean;

public class InitParam {

    String paramName;
    String paramValue;

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamValue() {
        return paramValue;
    }


    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
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

        InitParam initParam = (InitParam) o;


        if (getParamName()==null) {
            if (initParam.getParamName() != null) {
                return false;
            }
        } else if (!getParamName().equals(initParam.getParamName())) {
            return false;
        }


        if (getParamValue()==null) {
            if (initParam.getParamValue() != null) {
                return false;
            }
        } else if (!getParamValue().equals(initParam.getParamValue())) {
            return false;
        }

        return true;

    }

    @Override
    public int hashCode() {
        int result = getParamName().hashCode();
        result = 31 * result + getParamValue().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "InitParam{" +
                "paramName='" + paramName + '\'' +
                ", paramValue='" + paramValue + '\'' +
                '}';
    }
}
