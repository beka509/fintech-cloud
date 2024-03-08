package kz.fintech.dbservice.repos;

import kz.fintech.dbservice.entities.OverdueReasonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OverdueReasonRepository extends JpaRepository<OverdueReasonEntity, Integer> {
}
