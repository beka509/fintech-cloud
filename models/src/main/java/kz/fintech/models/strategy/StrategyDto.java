package kz.fintech.models.strategy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StrategyDto {
    private Integer id;
    private String name;
    private String description;
    private Integer segmentId;
    private String segmentName; // Имя сегмента
    private String timeStart;
}
