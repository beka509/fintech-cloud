package kz.fintech.models.dictionary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


//модель - Справочник Скрипты
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Scripts {
    private Integer scriptsId;
    private String name;
    private String code;
}
