package kz.fintech.starter.bpm.exceptions;

public class NoStackTraceException extends RuntimeException {

    private static final long serialVersionUID = -3027065171239936289L;

    public NoStackTraceException(String message) {
        super(message);
    }
}
