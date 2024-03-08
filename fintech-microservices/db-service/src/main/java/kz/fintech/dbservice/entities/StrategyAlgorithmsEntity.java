package kz.fintech.dbservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(schema = "public", name = "strategy_algorithms")
public class StrategyAlgorithmsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="strategy_algorithms_id")
    private Integer strategyAlgorithmsId;

    @ManyToOne
    @JoinColumn(name = "strategy_id")
    private StrategyEntity strategyItem;

    @ManyToOne
    @JoinColumn(name = "channel_Id")
    private ChannelsEntity channelItem;

    @ManyToOne
    @JoinColumn(name = "comm_fq_id")
    private CommFqEntity commFqItem;

    @ManyToOne
    @JoinColumn(name = "sequence_id")
    private SequencesEntity sequenceItem;

    @ManyToOne
    @JoinColumn(name = "scripts_id")
    private ScriptsEntity scriptsItem;
}
