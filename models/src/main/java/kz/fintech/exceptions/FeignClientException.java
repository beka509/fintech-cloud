package kz.fintech.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
public class FeignClientException extends RuntimeException {

    private final int status;
    private final String code;

    public FeignClientException(int status, String message) {
        super(message);
        this.code = message.contains(":-") ? message.substring(0, message.indexOf(":-")) : "";
        this.status = status;
    }
}
