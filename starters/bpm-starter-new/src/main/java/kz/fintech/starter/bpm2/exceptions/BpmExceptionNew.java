package kz.fintech.starter.bpm2.exceptions;

import lombok.Getter;

public class BpmExceptionNew extends RuntimeException {

    private static final long serialVersionUID = -1091390238876735734L;

    @Getter
    private final String errorCode;

    public BpmExceptionNew(String errorCode) {
        this.errorCode = errorCode;
    }

    public BpmExceptionNew(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
