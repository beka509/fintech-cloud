package kz.fintech.models.exceptions;

import lombok.Getter;

public class LimitMissingException extends RuntimeException {

    @Getter
    private final String code;

    public LimitMissingException(String code, String message) {
        super(message);
        this.code = code;
    }
}
