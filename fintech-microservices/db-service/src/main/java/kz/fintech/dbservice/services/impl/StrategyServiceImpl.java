package kz.fintech.dbservice.services.impl;
import kz.fintech.dbservice.entities.StrategyEntity;
import kz.fintech.dbservice.repos.StrategyRepository;
import kz.fintech.dbservice.services.StrategyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StrategyServiceImpl  implements StrategyService {
    private final StrategyRepository strategyRepository;
    public StrategyEntity createStrategyItem(StrategyEntity strategyItem) {
        return strategyRepository.save(strategyItem);
    }

    public StrategyEntity getStrategyItemById(Integer id) {
        return strategyRepository.findById(id).orElse(null);
    }

    public List<StrategyEntity> getAllStrategyItems() {
        return strategyRepository.findAll();
    }

    public StrategyEntity updateStrategyItem(Integer id, StrategyEntity strategyItem) {
        if (strategyRepository.existsById(id)) {
            strategyItem.setId(id);
            return strategyRepository.save(strategyItem);
        }
        return null;
    }

    public void deleteStrategyItem(Integer id) {
        strategyRepository.deleteById(id);
    }
}
