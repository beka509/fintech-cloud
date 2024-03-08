package kz.fintech.models.excel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientInformationResponse {
    private Integer clientId;
    private String fullName;
    private String iin;
    private String phoneNumber;
    private String phoneNumber2;
    private String email;
    private String residenceAddress;
    private String registrationAddress;
    private int loanAmount;
    private int totalDue;
    private int paymentAmount;
    private int overdueAmount;
    private int penaltyAmount;
    private int[] paymentDate;
    private int overdueDays;
    private int[] repaymentDate;
    private String productType;
    private String productName;
    private String contractNumber;
    private int[] contractOpenDate;
    private int[] dateEnd;
    private Boolean isArc;
    private int[] createDate;
    private String companyId;
    private String companyName;
}
