package kz.fintech.models.loaner;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LoanerContactDto {
    private Integer contactId;
    private String phoneNumber;
    private String contactType;
    private LocalDateTime changeDate;
    private String contactName;
    private Boolean isCurrent;
    private Integer priority;
    private Integer loanerId; // Ссылка на LoanerEntity
}
