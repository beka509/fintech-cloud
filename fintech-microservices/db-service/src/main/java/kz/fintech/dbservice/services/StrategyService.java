package kz.fintech.dbservice.services;

import kz.fintech.dbservice.entities.StrategyEntity;

import java.util.List;

public interface StrategyService {
    StrategyEntity createStrategyItem(StrategyEntity strategyItem);
    StrategyEntity getStrategyItemById(Integer id);
    List<StrategyEntity> getAllStrategyItems();
    StrategyEntity updateStrategyItem(Integer id, StrategyEntity strategyItem);
    void deleteStrategyItem(Integer id);
}
