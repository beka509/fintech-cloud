package kz.fintech.dbservice.services;

import kz.fintech.dbservice.entities.StrategySegmentsEntity;

import java.util.List;

public interface StrategySegmentsService {
    StrategySegmentsEntity createStrategySegments(StrategySegmentsEntity strategySegments);
    StrategySegmentsEntity getStrategySegmentsById(Integer id);
    List<StrategySegmentsEntity> getAllStrategySegments();
    StrategySegmentsEntity updateStrategySegments(Integer id, StrategySegmentsEntity strategySegments);
    void deleteStrategySegments(Integer id);
}
