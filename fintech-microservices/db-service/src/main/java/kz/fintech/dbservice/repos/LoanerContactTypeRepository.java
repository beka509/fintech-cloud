package kz.fintech.dbservice.repos;

import kz.fintech.dbservice.entities.LoanerContactTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanerContactTypeRepository extends JpaRepository<LoanerContactTypeEntity, Integer> {
}
