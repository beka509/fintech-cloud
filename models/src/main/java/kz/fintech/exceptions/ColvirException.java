package kz.fintech.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
public class ColvirException extends RuntimeException {

    private static final long serialVersionUID = -5965188760258118349L;

    private final int status;
    private final String code;

    public ColvirException(int status, String message) {
        super(message);
        this.code = message.contains(":-") ? message.substring(0, message.indexOf(":-")) : "";
        this.status = status;
    }
}
