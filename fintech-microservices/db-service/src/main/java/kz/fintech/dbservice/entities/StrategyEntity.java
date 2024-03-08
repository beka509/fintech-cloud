package kz.fintech.dbservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.sql.Time;

@Entity
@Data
@NoArgsConstructor
@Table(schema = "public", name = "strategy")
public class StrategyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "segment_id")
    private SegmentsEntity segmentItem;

    @Column(name="time_start")
    private String timeStart;

}
