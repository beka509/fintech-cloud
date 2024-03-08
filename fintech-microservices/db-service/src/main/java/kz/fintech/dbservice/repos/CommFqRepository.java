package kz.fintech.dbservice.repos;

import kz.fintech.dbservice.entities.CommFqEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommFqRepository extends JpaRepository<CommFqEntity, Integer> {
}
