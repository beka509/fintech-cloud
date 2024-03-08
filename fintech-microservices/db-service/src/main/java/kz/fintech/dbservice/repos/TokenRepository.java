package kz.fintech.dbservice.repos;

import kz.fintech.dbservice.entities.RefreshTokenEntity;
import kz.fintech.models.auth.RefreshTokenDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<RefreshTokenEntity, Long> {

    void deleteByToken(String token);

    Optional<RefreshTokenDto> findByToken(String token);
}
