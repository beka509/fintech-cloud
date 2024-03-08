package kz.fintech.exceptions;

public class ConversionException extends RuntimeException {

    private static final long serialVersionUID = 494353756784279045L;

    public ConversionException() {
        super();
    }

    public ConversionException(String message) {
        super(message);
    }

    public ConversionException(String message, Throwable cause) {
        super(message, cause);
    }
}
