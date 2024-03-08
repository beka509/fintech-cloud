package kz.fintech.models.exceptions;

import lombok.Getter;

public class ValidationException extends RuntimeException {

    @Getter
    private final String code;

    public ValidationException(String code, String message) {
        super(message);
        this.code = code;
    }
}
