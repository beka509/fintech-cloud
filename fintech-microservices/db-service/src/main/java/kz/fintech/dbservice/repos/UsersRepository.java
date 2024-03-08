package kz.fintech.dbservice.repos;

import kz.fintech.dbservice.entities.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, Long> {
    UsersEntity findByUsername(String username);

}
