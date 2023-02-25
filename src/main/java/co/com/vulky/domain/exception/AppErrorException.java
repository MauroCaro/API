package co.com.vulky.domain.exception;

public class AppErrorException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = -5953878320631555188L;

    public AppErrorException(String message) {
        super(message);
    }

    public AppErrorException(String message, Throwable cause) {
        super(message, cause);
    }

}
