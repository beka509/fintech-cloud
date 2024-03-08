package kz.fintech.dbservice.services;

import kz.fintech.dbservice.entities.StrategyAlgorithmsEntity;
import java.util.List;

public interface StrategyAlgorithmsService {
    StrategyAlgorithmsEntity createStrategyAlgorithm(StrategyAlgorithmsEntity strategyAlgorithm);
    StrategyAlgorithmsEntity getStrategyAlgorithmById(Integer id);
    List<StrategyAlgorithmsEntity> getAllStrategyAlgorithms();
    StrategyAlgorithmsEntity updateStrategyAlgorithm(Integer id, StrategyAlgorithmsEntity strategyAlgorithm);
    void deleteStrategyAlgorithm(Integer id);
//    List<StrategyAlgorithmsEntity> getStrategyAlgorithmsByStrategyId(Integer strategyId);
}
