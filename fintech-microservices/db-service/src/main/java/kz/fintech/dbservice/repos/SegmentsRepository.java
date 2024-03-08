package kz.fintech.dbservice.repos;

import kz.fintech.dbservice.entities.SegmentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SegmentsRepository extends JpaRepository<SegmentsEntity, Integer> {
}
