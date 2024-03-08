package kz.fintech.dbservice.repos;

import kz.fintech.dbservice.entities.LoanerCallResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanerCallResultRepository extends JpaRepository<LoanerCallResultEntity, Integer> {
}
