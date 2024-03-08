package kz.fintech.dbservice.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@NoArgsConstructor
@Data
@Entity
@Table(schema = "public", name = "call_result_dictionary")
public class CallResultDictionaryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer resultId;

    private String name;
}
