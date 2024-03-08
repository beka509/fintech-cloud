package kz.fintech.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiError {
    private int status;
    private String code;
    private String message;
    @Singular
    private List<FieldError> fieldErrors;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private ZonedDateTime timestamp;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class FieldError {
        private Object rejectedValue;
        private String field;
        private String message;
    }
}
