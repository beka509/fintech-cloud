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
public class PostBuilding {
    private String streetId;
    private LocalizedString streetName;
    private String zipCode;
    private LocalizedString name;
}
