package kz.fintech.dbservice.repos;

import kz.fintech.dbservice.entities.StrategyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StrategyRepository extends JpaRepository<StrategyEntity, Integer> {
}
