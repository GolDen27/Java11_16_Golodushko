package by.tc.xml.service.exception;

public class ServiceExceptinon extends Exception {
    public ServiceExceptinon() {
    }

    public ServiceExceptinon(String message) {
        super(message);
    }

    public ServiceExceptinon(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceExceptinon(Throwable cause) {
        super(cause);
    }

}
