package kz.fintech.models.strategy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StrategyAlgorithmsDto {
    private Integer strategyAlgorithmsId;
    private Integer strategyId;
    private Integer channelId;
    private Integer commFqId;
    private Integer sequenceId;
    private Integer scriptsId;
    private String strategyName;
    private String channelName;
    private String commFqName;
    private String sequenceName;
    private String scriptsName;
}
