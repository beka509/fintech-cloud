package kz.fintech.models.refs.location;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GovCountry {
    private int id;
    private Boolean isMarkedForDelete;
    private Date modifiedDate;
    private Integer codeFl;
    private String code;
    private String nameCountryRu;
    private String nameCountryKz;
}
