package kz.fintech.dbservice.repos;

import kz.fintech.dbservice.entities.ScriptsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScriptsRepository extends JpaRepository<ScriptsEntity, Integer> {
}
