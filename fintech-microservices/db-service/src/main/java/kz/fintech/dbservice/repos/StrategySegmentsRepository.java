package kz.fintech.dbservice.repos;

import kz.fintech.dbservice.entities.StrategySegmentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StrategySegmentsRepository extends JpaRepository<StrategySegmentsEntity, Integer> {
}
