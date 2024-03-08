package kz.fintech.models.loaner;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LoanerCommHistoryDto {
    private Integer commHistoryId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String communicationDirection;
    private String contactNumber;
    private String result;
    private Integer managerId;
    private Integer clientId;
}
