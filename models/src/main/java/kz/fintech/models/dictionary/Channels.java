package kz.fintech.models.dictionary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


//модель - Справочник Канал
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Channels {
    private Integer channelsId;
    private String name;
}
