package kz.fintech.dbservice.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import jakarta.persistence.*;

@NoArgsConstructor
@Data
@Entity
@Table(schema = "public", name = "contact")
public class ContactEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contactId;
    private String phoneNumber;
    private int clientId;
    private String homeAddress;
    private String workPhone;
    private String telecomOperator;
    private boolean verified;
    private String typeName;
//    private String contactFio;
    private LocalDate changeDate;
//    private String userName;

}
