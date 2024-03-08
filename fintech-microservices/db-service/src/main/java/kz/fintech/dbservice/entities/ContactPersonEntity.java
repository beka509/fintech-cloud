package kz.fintech.dbservice.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@NoArgsConstructor
@Data
@Entity
@Table(schema = "public", name = "contract_person")
public class ContactPersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="person_id")
    private Integer personId;

    private String name;
}
