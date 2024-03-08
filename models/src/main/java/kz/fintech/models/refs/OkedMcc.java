package kz.fintech.models.refs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OkedMcc {
    private String okedCode;
    private String mcc;
    private String tariffEcom;
    private String tariffEpos;
    private String tariffEposNoLimit;
}
