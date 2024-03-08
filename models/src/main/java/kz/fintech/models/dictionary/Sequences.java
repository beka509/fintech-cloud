package kz.fintech.models.dictionary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//модель - Справочник Последовательность
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Sequences {
    private Integer sequencesId;
    private String name;
}
