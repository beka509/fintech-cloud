package kz.fintech.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiRequest<T> implements Serializable {
    private String refer;
    private String ipAddress;
    private Integer userId;
    private T payload;

    public static <T> ApiRequest<T> build(String refer, String ipAddress, Integer userId, T payload) {
        return new ApiRequest<>(refer, ipAddress, userId, payload);
    }
}
