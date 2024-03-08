package kz.fintech.models.refs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class User implements Serializable {
    private static final long serialVersionUID = 4660950030935589022L;
    private int id;
    private OrgUnit orgUnit;
    private OrgUnit currentOrgUnit;
    private String accountName;
    private String lastName;
    private String patronymic;
    private String firstName;
    private String postCode;
    private String postName;
    private String tabNumber;
    private String email;
    private String phoneNumber;
    private String photoUrl;
    private String bsAccountName;
    private String intPhoneNumber;
    private String terminalId;
    private boolean monitorTerminal;
    private Curator curator;
    @Builder.Default
    private Boolean isHead = false;
    @Builder.Default
    private boolean isArc = false;
    @Builder.Default
    private Set<String> roles = new HashSet<>();
    @Builder.Default
    private Set<String> privileges = new HashSet<>();
    @Builder.Default
    private Set<String> groups = new HashSet<>();
    @Builder.Default
    private Set<String> substituteFor = new HashSet<>();
    private String branchName;

    @JsonIgnore
    public String fullName() {
        if (id == 0) return "";
        return String.format("%s %s%s", lastName, firstName, patronymic != null && patronymic.length() > 0 ? " " + patronymic : "");
    }

    @JsonIgnore
    public String shortName() {
        if (id == 0) return "";
        return String.format("%s %s%s", lastName, firstName != null && firstName.length() > 0 ? firstName.substring(0, 1) + "." : "",
                patronymic != null && patronymic.length() > 0 ? patronymic.substring(0, 1) + "." : "");
    }
}
