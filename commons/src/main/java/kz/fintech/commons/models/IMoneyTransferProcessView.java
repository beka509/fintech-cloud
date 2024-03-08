package kz.fintech.commons.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IMoneyTransferProcessView {
    private Integer initiatorId;
    private String processInstanceId;
    private String requestNumber;
    private String fullName;
    private String iin;
    private Date startTime;
    private Double transferAmount;
    private String currency;
    private String accountNumber;
    private String status;
}
