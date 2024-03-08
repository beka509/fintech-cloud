package kz.fintech.dbservice.repos;

import feign.Param;
import kz.fintech.dbservice.entities.ClientEntity;
import kz.fintech.dbservice.entities.SegmentsEntity;
import kz.fintech.models.excel.ExcelExportRequestRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, String> {
    @Query(value = "select new kz.fintech.models.excel.ExcelExportRequestRepo(cl.clientId, cl.fullName, cl.iin, co.phoneNumber, co.workPhone, " +
            "a.email, a.residenceAddress, a.registrationAddress, ct.loanAmount, ct.totalDue, ct.paymentAmount, ct.overdueAmount, " +
            "ct.penaltyAmount, ct.paymentDate, ct.overdueDays, ct.paymentDate, ct.productType, ct.productName, ct.contractNumber, ct.endDate, ct.endDate," +
            "ct.isArc, ct.createDate, ct.companyId, ct.companyName) " +
            "from ClientEntity cl " +
            "left join ContactEntity co on cl.clientId=co.clientId " +
            "left join AddressEntity a on cl.clientId=a.clientId " +
            "left join ContractEntity ct on cl.clientId = ct.clientId " +
            "where ct.createDate = (select max(ud.createDate) from ContractEntity ud where ud.clientId = cl.clientId) " +
            "ORDER BY ct.createDate DESC ")
    List<ExcelExportRequestRepo> getList();


    @Query(value = "select new kz.fintech.models.excel.ExcelExportRequestRepo(cl.clientId, cl.fullName, cl.iin, co.phoneNumber, co.workPhone, " +
            "a.email, a.residenceAddress, a.registrationAddress, ct.loanAmount, ct.totalDue, ct.paymentAmount, ct.overdueAmount, " +
            "ct.penaltyAmount, " +
            "ct.paymentDate, ct.overdueDays, ct.paymentDate, ct.productType, ct.productName, ct.contractNumber, ct.endDate, ct.endDate," +
            "ct.isArc, ct.createDate, ct.companyId, ct.companyName) " +
            "from ClientEntity cl " +
            "left join ContactEntity co on cl.clientId = co.clientId " +
            "left join AddressEntity a on cl.clientId = a.clientId " +
            "left join ContractEntity ct on cl.clientId = ct.clientId " +
            "join SegmentByClientsEntity s on cl.clientId = s.client.clientId " +
            "where s.segment = :segment")
    List<ExcelExportRequestRepo> findListBySegment(@Param("segment") SegmentsEntity segment);


    ClientEntity findClientEntitiesByClientId(Integer clientId);

    List<ClientEntity> findAll();
}
