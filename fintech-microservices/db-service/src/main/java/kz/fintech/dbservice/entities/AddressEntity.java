package kz.fintech.dbservice.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@NoArgsConstructor
@Data
@Entity
@Table(schema = "public", name = "address")
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int addressId;
    private int clientId;
    private String email;
    private String employerEmail;
    private String residenceAddress;
    private String registrationAddress;
    private String workAddress;
}
