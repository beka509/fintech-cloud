package kz.fintech.models.refs;

import kz.fintech.models.LocalizedString;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class OrgUnit implements Serializable {

    public static final String HQ_CODE = "100000";

    private static final long serialVersionUID = -875640390671459779L;

    private int id;
    private Integer parentId;
    private OrgUnit branch;
    @Builder.Default
    private List<OrgUnit> children = new ArrayList<>();
    private LocalizedString name;
    private LocalizedString longName;
    private LocalizedString shortName;
    private String code;
    private User curator;

    private String zipCode;
    private LocalizedString city;
    private LocalizedString address;
    private LocalizedString branchName;
    private String bin;
    private String bik;
    private String phones;
    private String emails;
    private String fax;
    private String corrAccountKzt;
    private String corrAccountUsd;
    private Boolean isArc;
}
