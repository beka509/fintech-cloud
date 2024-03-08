package kz.fintech.dbservice.repos;

import kz.fintech.dbservice.entities.ContactPersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactPersonRepository extends JpaRepository<ContactPersonEntity, Integer> {
}
