package kz.fintech.dbservice.repos;

import kz.fintech.dbservice.entities.LoanerCallResultDemoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanerCallResultDemoRepository extends JpaRepository<LoanerCallResultDemoEntity, Integer> {
    List<LoanerCallResultDemoEntity> findByClientId(Integer clientId);
}
