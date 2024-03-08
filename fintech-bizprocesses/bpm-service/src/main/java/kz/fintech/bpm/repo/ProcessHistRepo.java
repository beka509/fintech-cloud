package kz.fintech.bpm.repo;

import kz.fintech.bpm.model.entity.ActHiProcinst;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Set;

public interface ProcessHistRepo extends JpaRepository<ActHiProcinst, String> {

    @Query(value = "select DISTINCT ahp.processInstanceId from ActHiProcinst as ahp left join ahp.processDefinition as pd " +
            " where ahp.startUserId = :userId " +
            " and (upper(pd.processDefinitionName)  like upper(concat('%',:searchKey,'%'))" +
            "      or  to_char(ahp.startTime,'DD.MM.YYYY HH24:MI')  like upper(concat('%',:searchKey,'%'))" +
            "      or to_char(ahp.endTime,'DD.MM.YYYY HH24:MI') like upper(concat('%',:searchKey,'%'))" +
            "      or upper(ahp.state) in (:statuses))")
    Set<String> getProcInstIdsBySearchKey(@Param("userId") String userId
            , @Param("searchKey") String searchKey
            , @Param("statuses") List<String> statuses);

    @Query(value = "select DISTINCT ahp.processInstanceId from ActHiProcinst as ahp left join ahp.processDefinition as pd " +
            " where ahp.startUserId in :userIdList ")
    Set<String> getProcInstIdsByUserIdList(@Param("userIdList") Set<String> userIdList);
}