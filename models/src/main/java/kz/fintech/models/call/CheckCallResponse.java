package kz.fintech.models.call;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckCallResponse {
    private String status;

    @JsonProperty("call_info")
    private List<Object> callInfo;
}
