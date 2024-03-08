package kz.fintech.exceptions;

public class RetryableException extends RuntimeException {
    private static final long serialVersionUID = 4749520518318176373L;

    public RetryableException(String message, Throwable cause) {
        super(message, cause);
    }
}
