package kz.fintech.dbservice.services;

import kz.fintech.dbservice.entities.SegmentsEntity;

import java.util.List;

public interface SegmentsService {
    SegmentsEntity createSegments(SegmentsEntity segments);
    SegmentsEntity getSegmentsById(Integer id);
    List<SegmentsEntity> getAllSegments();
    SegmentsEntity updateSegments(Integer id, SegmentsEntity segments);
    void deleteSegments(Integer id);
}
