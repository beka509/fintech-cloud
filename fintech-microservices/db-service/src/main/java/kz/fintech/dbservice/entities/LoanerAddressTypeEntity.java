package kz.fintech.dbservice.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@NoArgsConstructor
@Data
@Entity
@Table(schema = "public", name = "loaner_address_type")
public class LoanerAddressTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="type_id")
    private Integer typeId;

    private String name;
}
