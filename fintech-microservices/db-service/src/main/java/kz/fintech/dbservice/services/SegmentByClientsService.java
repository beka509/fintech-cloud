package kz.fintech.dbservice.services;

import kz.fintech.models.strategy.SegmentByClientsDTO;
import kz.fintech.models.strategy.SegmentDto;

import java.util.List;

public interface SegmentByClientsService {
    SegmentByClientsDTO createSegmentByClients(SegmentByClientsDTO segmentByClientsDTO);
    SegmentByClientsDTO getSegmentByClientsById(Integer id);
    List<SegmentByClientsDTO> getAllSegmentByClients();
    SegmentByClientsDTO updateSegmentByClients(Integer id, SegmentByClientsDTO updatedSegmentByClientsDTO);
    void deleteSegmentByClients(Integer id);

    void addClientsToSegment(Integer segmentId, List<Integer> clientIds);

    void deleteSegmentByClientsBySegmentId(Integer segmentId);
    void createSegmentAndAddClients(SegmentDto segmentDto, List<Integer> clientIds);

}
