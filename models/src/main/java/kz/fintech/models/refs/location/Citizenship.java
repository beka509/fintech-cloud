package kz.fintech.models.refs.location;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Citizenship {
    private int id;
    private String code;
    private String ruName;
    private String kzName;
    private Boolean isMarkedForDelete;
    private Date modifiedDate;
}
