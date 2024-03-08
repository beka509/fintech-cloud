package kz.fintech.dbservice.repos;

import kz.fintech.dbservice.entities.LoanerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanerRepository extends JpaRepository<LoanerEntity, Integer> {
}
