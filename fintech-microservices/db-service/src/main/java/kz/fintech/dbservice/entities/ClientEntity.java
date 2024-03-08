package kz.fintech.dbservice.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@Data
@Entity
@Table(schema = "public", name = "client")
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer clientId;
    private String fullName;
    private String iin;
    private String identityNumber;
    private LocalDate issueDate;
    private String issuedBy;
    private LocalDate expirationDate;
    private Integer age;
    private String gender;
    private String maritalStatus;
    private String socialStatus;
    private String organizationName;
    private String position;
    private Integer averageSalary;
}
