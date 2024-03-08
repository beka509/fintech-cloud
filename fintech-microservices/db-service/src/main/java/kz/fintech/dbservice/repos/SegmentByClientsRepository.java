package kz.fintech.dbservice.repos;

import feign.Param;
import kz.fintech.dbservice.entities.SegmentByClientsEntity;
import kz.fintech.dbservice.entities.SegmentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import java.util.List;

@Repository
public interface SegmentByClientsRepository extends JpaRepository<SegmentByClientsEntity, Integer> {

    void deleteBySegment(SegmentsEntity segment);
    void deleteAllBySegment(SegmentsEntity segment);

    @Modifying
    @Transactional
    @Query("DELETE FROM SegmentByClientsEntity s WHERE s.segment.segmentId = :segmentId")
    void deleteBySegmentId(@Param("segmentId") Integer segmentId);

    @Modifying
    @Transactional
    @Query("select s.client, s.id, s.segment FROM SegmentByClientsEntity s WHERE s.segment.segmentId = :segmentId")
    List<SegmentByClientsEntity> findBySegmentId(@Param("segmentId") Integer segmentId);
}
