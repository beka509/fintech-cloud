package kz.fintech.dbservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.sql.Time;

@Entity
@Data
@NoArgsConstructor
@Table(schema = "public", name = "strategy_segments")
public class StrategySegmentsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer strategySegmentsId;

    @ManyToOne
    @JoinColumn(name = "strategy_id")
    private StrategyEntity strategyItem;

    private Integer segmentByClientsId;
    private Time timeStart;
}
