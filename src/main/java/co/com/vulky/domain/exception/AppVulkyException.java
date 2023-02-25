package co.com.vulky.domain.exception;

public class AppVulkyException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = -5953878320631555188L;

    public AppVulkyException(String message) {
        super(message);
    }

    public AppVulkyException(String message, Throwable cause) {
        super(message, cause);
    }

}
