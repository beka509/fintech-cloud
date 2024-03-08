package kz.fintech.dbservice.repos;

import kz.fintech.dbservice.entities.LoanerCommHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanerCommHistoryRepository extends JpaRepository<LoanerCommHistoryEntity, Integer> {
    List<LoanerCommHistoryEntity> findByClientId(Integer clientId);

}
