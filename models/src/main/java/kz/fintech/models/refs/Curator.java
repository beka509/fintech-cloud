package kz.fintech.models.refs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Curator implements Serializable {
    private String curatorCode;
    private String curatorName;
    private String curatorLongname;
    private User user;
    private List<OrgUnit> orgUnits;
}
