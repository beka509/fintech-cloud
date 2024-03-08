package kz.fintech.dbservice.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@NoArgsConstructor
@Data
@Entity
@Table(schema = "public", name = "overdue_reason")
public class OverdueReasonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer personId;

    private String name;
}
