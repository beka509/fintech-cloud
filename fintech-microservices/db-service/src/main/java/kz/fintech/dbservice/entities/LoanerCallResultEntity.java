package kz.fintech.dbservice.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Entity
@Table(schema = "public", name = "loaner_call_result")
public class LoanerCallResultEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="call_result_id")
    private Integer callResultId;

    @Column(name="contact_person_id")
    private Integer contactPersonId;

    @Column(name="overdue_reason_id")
    private Integer overdueReasonId;

    @Column(name="result_id")
    private Integer resultId;

    @Column(name="promise_date")
    private LocalDateTime promiseDate;

    @Column(name="promise_amount")
    private Integer promiseAmount;
    private String comment;
    @ManyToOne
    @JoinColumn(name = "loaner_id")
    private LoanerEntity loanerItem;
}
