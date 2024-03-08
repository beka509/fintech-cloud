package kz.fintech.dbservice.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Entity
@Table(schema = "public", name = "loaner_address")
public class LoanerAddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="address_id")
    private Integer addressId;

    @ManyToOne
    @JoinColumn(name = "address_type_id")
    private LoanerAddressTypeEntity loanerAddressTypeItem;

    @Column(name="change_date")
    private LocalDateTime changeDate;
    private String region;
    private String city;
    private String street;
    private String zipCode;
    private String kato;
    @Column(name="manager_id")
    private Integer managerId;

    @ManyToOne
    @JoinColumn(name = "loaner_id")
    private LoanerEntity loanerItem;


}
