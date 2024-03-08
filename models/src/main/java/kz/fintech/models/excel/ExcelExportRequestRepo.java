package kz.fintech.models.excel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExcelExportRequestRepo {
    private Integer clientId;
    private String fio;
    private String iin;
    private String phoneNumber;
    private String phoneNumber2;
    private String email;
    private String residenceAddress;
    private String registrAddress;
    private int loanAmount;
    private int totalDebtAmount;
    private int paymentAmount;
    private int overdueAmount;
    private int penaltyAmount;
    private OffsetDateTime paymentDate;
    private int overdueDay;
    private OffsetDateTime repaymentDate;
    private String productType;
    private String productName;
    private String contractNumber;
    private OffsetDateTime contractOpenDate;
    private OffsetDateTime dateEnd;
    private Boolean isArc;
    private OffsetDateTime createDate;
    private String companyId;
    private String companyName;
}
