package kz.fintech.models.refs;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrgUnitCustom {

    private Integer id;
    private Integer parentId;
    private String orgUnitName;
    private Integer cnt;
    private List<OrgUnitCustom> children;
}
