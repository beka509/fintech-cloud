package kz.fintech.models.call;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CdrGeneral {
    private String start;
    private String srcNum;
    private String dstNum;
    private String uniqueid;
    private Integer billsec;
}
