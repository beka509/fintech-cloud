package kz.fintech.models.refs;

import kz.fintech.models.LocalizedString;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IdentityDocumentAuthority {
    private int id;
    private LocalizedString name;
    private String code;
    private String govCode;
}
