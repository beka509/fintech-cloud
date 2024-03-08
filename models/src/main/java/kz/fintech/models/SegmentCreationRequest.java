package kz.fintech.models;

import kz.fintech.models.strategy.SegmentDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SegmentCreationRequest {
    private SegmentDto segmentDto;
    private List<Integer> clientIds;

}