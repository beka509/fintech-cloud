package kz.fintech.dbservice.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(schema = "public", name = "sequences")
public class SequencesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sequencesId;
    private String name;
}
