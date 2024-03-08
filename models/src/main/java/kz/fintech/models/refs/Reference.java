package kz.fintech.models.refs;

import kz.fintech.models.LocalizedString;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Reference {
    private String id;
    private String code;
    private String colvirCode;
    private LocalizedString name;
    private int sortOrder;
    private Boolean isArc;
}
