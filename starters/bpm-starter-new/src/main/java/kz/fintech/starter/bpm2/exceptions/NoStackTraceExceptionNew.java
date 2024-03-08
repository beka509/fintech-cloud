package kz.fintech.starter.bpm2.exceptions;

public class NoStackTraceExceptionNew extends RuntimeException {

    private static final long serialVersionUID = -3027065171239936289L;

    public NoStackTraceExceptionNew(String message) {
        super(message);
    }
}
