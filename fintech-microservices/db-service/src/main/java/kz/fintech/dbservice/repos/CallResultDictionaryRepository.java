package kz.fintech.dbservice.repos;

import kz.fintech.dbservice.entities.CallResultDictionaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CallResultDictionaryRepository extends JpaRepository<CallResultDictionaryEntity, Integer> {
}
