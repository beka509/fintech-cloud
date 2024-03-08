package kz.fintech.models.strategy;

import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SegmentByClientsDTO {
    private Integer id;
    private Integer segmentId;
    private Integer clientId;
}
