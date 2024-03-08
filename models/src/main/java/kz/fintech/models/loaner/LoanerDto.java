package kz.fintech.models.loaner;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LoanerDto {
    private Integer loanerId;
    private String lastName;
    private String firstName;
    private String middleName;
    private String iin;
    private String contractNumber;
    private String loanType;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Boolean overdue;
    private Double loanAmount;
    private String currency;
    private String issuingBranch;
    private Boolean isVip;
    private Boolean isBfl;
    private String guarantorLastName;
    private String guarantorFirstName;
    private String guarantorMiddleName;
    private String guarantorIin;
    private Boolean guarantorIsVip;
    private Boolean guarantorIsBfl;
    private Integer overdueDays;
    private Integer status;
}
