package kz.fintech.dbservice.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Entity
@Table(schema = "public", name = "loaner_call_result_demo")
public class LoanerCallResultDemoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "call_result_id")
    private Integer callResultId;

    @Column(name = "contact_person_name")
    private String contactPersonName;

    @Column(name = "overdue_reason")
    private String overdueReason;

    @Column(name = "result_desc")
    private String resultDesc;

    @Column(name = "promise_date")
    private LocalDateTime promiseDate;

    @Column(name = "comment")
    private String comment;

    @Column(name = "client_id")
    private Integer clientId;
}
