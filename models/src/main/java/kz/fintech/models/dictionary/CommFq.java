package kz.fintech.models.dictionary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


//модель - Справочник Частота коммуникации
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommFq {
    private Integer commFqId;
    private String name;
}
