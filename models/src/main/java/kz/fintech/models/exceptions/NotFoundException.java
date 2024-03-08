package kz.fintech.models.exceptions;

public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = 7081353304342342421L;

    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

