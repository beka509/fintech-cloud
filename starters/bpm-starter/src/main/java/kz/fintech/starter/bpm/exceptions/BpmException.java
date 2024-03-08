package kz.fintech.starter.bpm.exceptions;

import lombok.Getter;

public class BpmException extends RuntimeException {

    private static final long serialVersionUID = -1091390238876735734L;

    @Getter
    private final String errorCode;

    public BpmException(String errorCode) {
        this.errorCode = errorCode;
    }

    public BpmException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
