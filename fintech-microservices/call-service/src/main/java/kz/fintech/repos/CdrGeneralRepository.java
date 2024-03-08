package kz.fintech.repos;

import feign.Param;
import kz.fintech.entities.CdrGeneralEntity;
import kz.fintech.models.call.CdrGeneral;
import kz.fintech.models.call.CdrGeneralClass;
import kz.fintech.models.excel.ExcelExportRequestRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CdrGeneralRepository extends JpaRepository<CdrGeneralEntity, String> {
    @Query("SELECT new kz.fintech.models.call.CdrGeneralClass(" +
            "e.id, e.start, e.endTime, e.answer, e.srcChan, e.srcNum, e.dstChan, e.dstNum, " +
            "e.uniqueid, e.linkedid, e.did, e.disposition, e.recordingfile, " +
            "e.fromAccount, e.toAccount, e.dialStatus, e.appName, e.transfer, " +
            "e.isApp, e.duration, e.billsec, e.workCompleted, " +
            "e.srcCallId, e.dstCallId, e.verboseCallId) " +
            "FROM CdrGeneralEntity e WHERE e.srcNum = :srcNum " +
            "order by e.start desc ")
    List<CdrGeneralClass> findListBySrcNum(@Param("srcNum") String srcNum);

    @Query("SELECT new kz.fintech.models.call.CdrGeneralClass(" +
            "e.id, e.start, e.endTime, e.answer, e.srcChan, e.srcNum, e.dstChan, e.dstNum, " +
            "e.uniqueid, e.linkedid, e.did, e.disposition, e.recordingfile, " +
            "e.fromAccount, e.toAccount, e.dialStatus, e.appName, e.transfer, " +
            "e.isApp, e.duration, e.billsec, e.workCompleted, " +
            "e.srcCallId, e.dstCallId, e.verboseCallId) " +
            "FROM CdrGeneralEntity e WHERE e.dstNum = :dstNum " +
            "order by e.start desc ")
    List<CdrGeneralClass> findListByDstNum(@Param("dstNum") String dstNum);

    @Query("SELECT new kz.fintech.models.call.CdrGeneralClass(" +
            "e.id, e.start, e.endTime, e.answer, e.srcChan, e.srcNum, e.dstChan, e.dstNum, " +
            "e.uniqueid, e.linkedid, e.did, e.disposition, e.recordingfile, " +
            "e.fromAccount, e.toAccount, e.dialStatus, e.appName, e.transfer, " +
            "e.isApp, e.duration, e.billsec, e.workCompleted, " +
            "e.srcCallId, e.dstCallId, e.verboseCallId) " +
            "FROM CdrGeneralEntity e WHERE e.did = :did " +
            "order by e.start desc ")
    List<CdrGeneralClass> findListByDid(@Param("did") String did);

    @Query("SELECT new kz.fintech.models.call.CdrGeneralClass(" +
            "e.id, e.start, e.endTime, e.answer, e.srcChan, e.srcNum, e.dstChan, e.dstNum, " +
            "e.uniqueid, e.linkedid, e.did, e.disposition, e.recordingfile, " +
            "e.fromAccount, e.toAccount, e.dialStatus, e.appName, e.transfer, " +
            "e.isApp, e.duration, e.billsec, e.workCompleted, " +
            "e.srcCallId, e.dstCallId, e.verboseCallId) " +
            "FROM CdrGeneralEntity e WHERE e.uniqueid = :uniqueid")
    List<CdrGeneralClass> findListByUniqueid(@Param("uniqueid") String uniqueid);
}
