package kz.fintech.models.refs.location;

import kz.fintech.models.LocalizedString;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Country {
    public static final String KZ = "KZ";
    public static final String KZ_CODE = "398";

    private String code;
    private String alpha3Code;
    private String numericCode;
    private String phoneCode;
    private LocalizedString name;
    private LocalizedString shortName;
    private Boolean isOffshore;
}
