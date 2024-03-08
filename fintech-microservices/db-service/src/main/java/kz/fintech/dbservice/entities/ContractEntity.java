package kz.fintech.dbservice.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@NoArgsConstructor
@Data
@Entity
@Table(schema = "public", name = "contract")
public class ContractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contractId;
    private String contractNumber;
    private OffsetDateTime openDate;
    private OffsetDateTime endDate;
    private OffsetDateTime paymentDate;
    private int paymentAmount;
    private int overdueAmount;
    private int penaltyAmount;
    private int totalDue;
    private int overdueDays;
    private OffsetDateTime maxOverdueDayHistory;
    private int clientId;
    private OffsetDateTime repaymentDate;
    private int productId;
    private String productType;
    private String productName;
    private int productLife;
    private int loanAmount;
    private int countDelinquencies;
    private Boolean isArc;
    private OffsetDateTime createDate;
    private String companyId;
    private String companyName;


}
