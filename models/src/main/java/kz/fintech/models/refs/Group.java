package kz.fintech.models.refs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
//@Accessors(fluent = true, chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Group implements Serializable {

    private static final long serialVersionUID = -4668131677599009474L;
    private String groupCode;

    private String groupShortname;

    private String groupLongname;

    private Integer orgUnitId;

}
