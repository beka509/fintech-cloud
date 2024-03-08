package kz.fintech.dbservice.services.impl;

import kz.fintech.dbservice.entities.StrategyAlgorithmsEntity;
import kz.fintech.dbservice.repos.StrategyAlgorithmsRepository;
import kz.fintech.dbservice.services.StrategyAlgorithmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StrategyAlgorithmsServiceImpl implements StrategyAlgorithmsService {
    private final StrategyAlgorithmsRepository strategyAlgorithmsRepository;

    @Autowired
    public StrategyAlgorithmsServiceImpl(StrategyAlgorithmsRepository strategyAlgorithmsRepository) {
        this.strategyAlgorithmsRepository = strategyAlgorithmsRepository;
    }

    @Override
    public StrategyAlgorithmsEntity createStrategyAlgorithm(StrategyAlgorithmsEntity strategyAlgorithm) {
        return strategyAlgorithmsRepository.save(strategyAlgorithm);
    }

    @Override
    public StrategyAlgorithmsEntity getStrategyAlgorithmById(Integer id) {
        return strategyAlgorithmsRepository.findById(id).orElse(null);
    }

    @Override
    public List<StrategyAlgorithmsEntity> getAllStrategyAlgorithms() {
        return strategyAlgorithmsRepository.findAll();
    }

    @Override
    public StrategyAlgorithmsEntity updateStrategyAlgorithm(Integer id, StrategyAlgorithmsEntity strategyAlgorithm) {
        if (strategyAlgorithmsRepository.existsById(id)) {
            strategyAlgorithm.setStrategyAlgorithmsId(id);
            return strategyAlgorithmsRepository.save(strategyAlgorithm);
        }
        return null;
    }

    @Override
    public void deleteStrategyAlgorithm(Integer id) {
        strategyAlgorithmsRepository.deleteById(id);
    }

//    @Override
//    public List<StrategyAlgorithmsEntity> getStrategyAlgorithmsByStrategyId(Integer strategyId) {
//        return strategyAlgorithmsRepository.findByStrategyItem_StrategyId(strategyId);
//    }
}
