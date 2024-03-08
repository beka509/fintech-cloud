package kz.fintech.dbservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(schema = "public", name = "comm_fq")
public class CommFqEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commFqId;
    private String name;
}
