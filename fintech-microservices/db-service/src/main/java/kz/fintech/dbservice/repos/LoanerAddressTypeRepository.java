package kz.fintech.dbservice.repos;

import kz.fintech.dbservice.entities.LoanerAddressTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanerAddressTypeRepository extends JpaRepository<LoanerAddressTypeEntity, Integer> {
}
