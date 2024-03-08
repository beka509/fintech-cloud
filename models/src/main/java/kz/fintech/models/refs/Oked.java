package kz.fintech.models.refs;

import kz.fintech.models.LocalizedString;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Oked {
    private String code;
    private String parentCode;
    private LocalizedString name;
    private int level;
    private double profitability;
}
