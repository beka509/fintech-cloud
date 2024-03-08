package kz.fintech.dbservice.repos;

import kz.fintech.dbservice.entities.ClientEntity;
import kz.fintech.dbservice.entities.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<ContactEntity, Integer> {
}
