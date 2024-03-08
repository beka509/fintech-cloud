package kz.fintech.dbservice.repos;

import kz.fintech.dbservice.entities.LoanerAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanerAddressRepository extends JpaRepository<LoanerAddressEntity, Integer> {
}
