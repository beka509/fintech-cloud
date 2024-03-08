package kz.fintech.models.file.generate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Consent {

    private String managerName;
    private String authorityId;
    private Customer customer;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Customer{
        private String name;
        private String iin;
        private Date birthDate;
        private Document document;
    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Document{
        private String number;
        private String regAuthority;
        private Date regDate;
        private Date expDate;

    }
}
