package kz.fintech.dbservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(schema = "public", name = "segments")
public class SegmentsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer segmentId;
    private String name;
    private String descriptions;
}
