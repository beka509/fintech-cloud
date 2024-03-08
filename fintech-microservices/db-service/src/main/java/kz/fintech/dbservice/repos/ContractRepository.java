package kz.fintech.dbservice.repos;

import kz.fintech.dbservice.entities.ContractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContractRepository extends JpaRepository<ContractEntity, Integer> {
    @Query("SELECT c FROM ContractEntity c WHERE c.createDate = (SELECT MAX(u.createDate) FROM ContractEntity u WHERE u.clientId = c.clientId)")
    List<ContractEntity> findLatestContractsByClientId();
}
