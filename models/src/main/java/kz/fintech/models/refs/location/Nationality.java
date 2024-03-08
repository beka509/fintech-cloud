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
public class Nationality {
    private int id;
    private boolean isMarkedForDelete;
    private Date modifiedDate;
    private String codeFl;
    private String code;
    private String nameRus;
    private String nameKaz;
}
