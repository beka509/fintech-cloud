package kz.fintech.dbservice.repos;

import kz.fintech.dbservice.entities.StrategyAlgorithmsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StrategyAlgorithmsRepository extends JpaRepository<StrategyAlgorithmsEntity, Integer> {
//    List<StrategyAlgorithmsEntity> findByStrategyItem_StrategyId(Integer strategyId);

}

