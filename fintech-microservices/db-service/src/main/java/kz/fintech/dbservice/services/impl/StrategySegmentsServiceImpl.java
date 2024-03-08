package kz.fintech.dbservice.services.impl;

import kz.fintech.dbservice.entities.StrategySegmentsEntity;
import kz.fintech.dbservice.repos.StrategySegmentsRepository;
import kz.fintech.dbservice.services.StrategySegmentsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StrategySegmentsServiceImpl implements StrategySegmentsService {
    private final StrategySegmentsRepository strategySegmentsRepository;

    @Override
    public StrategySegmentsEntity createStrategySegments(StrategySegmentsEntity strategySegments) {
        return strategySegmentsRepository.save(strategySegments);
    }

    @Override
    public StrategySegmentsEntity getStrategySegmentsById(Integer id) {
        return strategySegmentsRepository.findById(id).orElse(null);
    }

    @Override
    public List<StrategySegmentsEntity> getAllStrategySegments() {
        return strategySegmentsRepository.findAll();
    }

    @Override
    public StrategySegmentsEntity updateStrategySegments(Integer id, StrategySegmentsEntity strategySegments) {
        if (strategySegmentsRepository.existsById(id)) {
            strategySegments.setStrategySegmentsId(id);
            return strategySegmentsRepository.save(strategySegments);
        }
        return null;
    }

    @Override
    public void deleteStrategySegments(Integer id) {
        strategySegmentsRepository.deleteById(id);
    }
}
