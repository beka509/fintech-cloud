package kz.fintech.dbservice.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;


@Data
@NoArgsConstructor
@Table(schema = "public", name = "channels")
@Entity
public class ChannelsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer channelsId;
    private String name;
}
