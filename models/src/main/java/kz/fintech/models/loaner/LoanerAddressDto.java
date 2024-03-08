package kz.fintech.models.loaner;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LoanerAddressDto {
    private Integer addressId;
    private Integer addressTypeId;
    private LocalDateTime changeDate;
    private String region;
    private String city;
    private String street;
    private String zipCode;
    private String kato;
    private Integer managerId;
    private Integer loanerId;
}
