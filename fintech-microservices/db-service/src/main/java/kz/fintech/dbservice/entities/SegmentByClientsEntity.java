package kz.fintech.dbservice.entities;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@NoArgsConstructor
@Data
@Table(schema = "public", name = "segment_by_clients")
public class SegmentByClientsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "segment_by_clients_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "segment_id")
    private SegmentsEntity segment;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientEntity client;
}
