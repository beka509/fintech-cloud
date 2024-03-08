package kz.fintech.dbservice.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Entity
@Table(schema = "public", name = "loaner_contact")
public class LoanerContactEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="contact_id")
    private Integer contactId;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="contact_type")
    private String contactType;

    @Column(name="change_date")
    private LocalDateTime changeDate;

    @Column(name="contact_name")
    private String contactName;

    @Column(name="is_current")
    private Boolean isCurrent;

    private Integer priority;

    @ManyToOne
    @JoinColumn(name = "loaner_id")
    private LoanerEntity loanerItem;
}
