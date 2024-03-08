package kz.fintech.models.call;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CallStatus {
    private String status;
    private String message;
    private List<CdrGeneralClass> data;
}
