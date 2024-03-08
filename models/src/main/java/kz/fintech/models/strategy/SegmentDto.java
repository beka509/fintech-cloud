package kz.fintech.models.strategy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SegmentDto {
    private Integer segmentId;
    private String name;
    private String descriptions;
}
