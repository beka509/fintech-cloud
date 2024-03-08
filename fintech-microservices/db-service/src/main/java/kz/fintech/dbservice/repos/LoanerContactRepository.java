package kz.fintech.dbservice.repos;

import kz.fintech.dbservice.entities.LoanerContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanerContactRepository extends JpaRepository<LoanerContactEntity, Integer> {
    List<LoanerContactEntity> findLoanerContactEntityByLoanerItem_LoanerId(Integer loanerId);
}
