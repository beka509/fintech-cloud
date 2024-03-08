package kz.fintech.models.refs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role implements Serializable {

    private static final long serialVersionUID = 6485456396389859711L;

    private String roleCode;
    private String shortName;
    private String longName;
    private Boolean isArc;
    private Boolean isDefaultRole;
    private List<Integer> privileges;
}
