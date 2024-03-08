package kz.fintech.dbservice.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Entity
@Table(schema = "public", name = "loaner_comm_history")
public class LoanerCommHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commHistoryId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String communicationDirection;
    private String contactNumber;
    private String result;
    private Integer managerId;
    private Integer clientId;
}
