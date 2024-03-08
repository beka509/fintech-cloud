package kz.fintech.models.whatsApp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendMessagesRequest {
    @JsonProperty("phoneNumber")
    private String phoneNumber;
    @JsonProperty("text")
    private String text;
}
