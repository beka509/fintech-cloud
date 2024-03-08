package kz.fintech.dbservice.entities;


import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Entity
@Table(schema = "public", name = "loaner")
public class LoanerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="loaner_id")
    private Integer loanerId;

    @Column(name="last_name")
    private String lastName;

    @Column(name="first_name")
    private String firstName;

    @Column(name="middle_name")
    private String middleName;

    private String iin;

    @Column(name="contract_number")
    private String contractNumber;

    @Column(name="loan_type")
    private String loanType;

    @Column(name="start_date")
    private LocalDateTime startDate;

    @Column(name="end_date")
    private LocalDateTime endDate;

    private Boolean overdue;

    @Column(name="loan_amount")
    private Double loanAmount;

    private String currency;

    @Column(name="issuing_branch")
    private String issuingBranch;

    @Column(name="is_vip")
    private Boolean isVip;

    @Column(name="is_bfl")
    private Boolean isBfl;

    @Column(name="guarantor_last_name")
    private String guarantorLastName;

    @Column(name="guarantor_first_name")
    private String guarantorFirstName;

    @Column(name="guarantor_middle_name")
    private String guarantorMiddleName;

    @Column(name="guarantor_iin")
    private String guarantorIin;

    @Column(name="guarantor_is_vip")
    private Boolean guarantorIsVip;

    @Column(name="guarantor_is_bfl")
    private Boolean guarantorIsBfl;

    @Column(name="overdueDays")
    private Integer overdue_days;

    private Integer status;

}
