package kz.fintech.dbservice.repos;

import kz.fintech.dbservice.entities.ChannelsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChannelsRepository extends JpaRepository<ChannelsEntity, Integer> {
}
