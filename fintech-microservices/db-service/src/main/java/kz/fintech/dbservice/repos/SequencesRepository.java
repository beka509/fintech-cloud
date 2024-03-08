package kz.fintech.dbservice.repos;

import kz.fintech.dbservice.entities.SequencesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SequencesRepository extends JpaRepository<SequencesEntity, Integer> {
}
