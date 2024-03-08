package kz.fintech.models.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientContact {

    private Integer contactId;

    private Integer clientId;

    private String homeAddress;

    private String phoneNumber;

    private String telecomOperator;

    private boolean verified;

    private String workPhone;
    private String typeName;
//    private String contactFio;
    private LocalDate changeDate;
//    private String userName;

}