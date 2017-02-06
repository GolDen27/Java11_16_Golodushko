package by.tc.parser.bean;

public class ErrorPage {

    String exceptionType;
    String errorCode;
    String location;

    public String getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(String exceptionType) {
        this.exceptionType = exceptionType;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

        ErrorPage errorPage = (ErrorPage) o;

        if (exceptionType==null) {
            if (errorPage.exceptionType != null) {
                return false;
            }
        } else if (!exceptionType.equals(errorPage.exceptionType)) {
            return false;
        }

        if (getErrorCode()==null) {
            if (errorPage.getErrorCode() != null) {
                return false;
            }
        } else if (!getErrorCode().equals(errorPage.getErrorCode())) {
            return false;
        }

        if (getLocation()==null) {
            if (errorPage.getLocation() != null) {
                return false;
            }
        } else if (!getLocation().equals(errorPage.getLocation())) {
            return false;
        }
        return true;

    }

    @Override
    public int hashCode() {
        int result = getExceptionType().hashCode();
        result = 31 * result + getErrorCode().hashCode();
        result = 31 * result + getLocation().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ErrorPage{" +
                "exceptionType='" + exceptionType + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
