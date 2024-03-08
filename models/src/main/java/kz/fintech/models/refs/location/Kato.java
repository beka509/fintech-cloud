package kz.fintech.models.refs.location;

import kz.fintech.models.LocalizedString;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Kato {
    private int id;
    private Integer parentId;
    private int katoObjectTypeId;
    private LocalizedString name;
    private String code;
    private String parentCode;
}
