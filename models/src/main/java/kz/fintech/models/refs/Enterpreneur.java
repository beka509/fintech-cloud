package kz.fintech.models.refs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Enterpreneur {
    private String iin;
    private String businessNameRu;
    private String lastname;
    private String firstname;
    private String middlename;
    private Integer isJointVenture;
    private String regCode;
    private String regName;
    private String begRegDate;
    private String endRegDate;
    private String unregReasonCode;
    private String unregReasonName;
    private String dateFromSuspended;
    private String suspendedState;
    private String dateFromInactive;
    private String dateToInactive;
    private String inactiveState;
}
