package kz.fintech.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {
    private boolean success;
    private String errorCode;
    private String errorMessage;
    private T payload;

    public static <T> Response<T> success(T payload) {
        return new Response<>(true, null, null, payload);
    }

    public static <T> Response<T> error(String errorCode, String errorMessage) {
        return new Response<>(false, errorCode, errorMessage, null);
    }
}
