package kz.fintech.models.call;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MakeCallRequest {

    @JsonProperty("from_number")
    private String fromNumber;

    @JsonProperty("to_number")
    private String toNumber;
}
