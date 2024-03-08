package kz.fintech.models.loaner;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LoanerCallResultDemoDto {
    private Integer callResultId;
    private String contactPersonName;
    private String overdueReason;
    private String resultDesc;
    private LocalDateTime promiseDate;
    private String comment;
    private Integer clientId;
}
