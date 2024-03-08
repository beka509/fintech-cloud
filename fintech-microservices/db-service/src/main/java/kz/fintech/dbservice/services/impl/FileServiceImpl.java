package kz.fintech.dbservice.services.impl;

import kz.fintech.dbservice.entities.*;
import kz.fintech.dbservice.repos.*;
import kz.fintech.dbservice.services.FileService;
import kz.fintech.dbservice.services.SegmentsService;
import kz.fintech.models.excel.ExcelExportIin;
import kz.fintech.models.excel.ExcelExportRequest;
import kz.fintech.models.excel.ExcelExportRequestRepo;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.time.OffsetDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FileServiceImpl implements FileService {
    public final ClientRepository clientRepository;
    public final ContactRepository contactRepository;
    public final AddressRepository addressRepository;
    public final ContractRepository contractRepository;
    public final SegmentsService segmentsService;
    public final SegmentByClientsRepository segmentByClientsRepository;
    public FileServiceImpl(ClientRepository clientRepository,
                           ContactRepository contactRepository,
                           AddressRepository addresRepository,
                           ContractRepository contractRepository,
                           SegmentsService segmentsService,
                           SegmentByClientsRepository segmentByClientsRepository) {
        this.clientRepository = clientRepository;
        this.contactRepository = contactRepository;
        this.addressRepository = addresRepository;
        this.contractRepository = contractRepository;
        this.segmentsService = segmentsService;
        this.segmentByClientsRepository = segmentByClientsRepository;
    }

    @Override
    public List<ExcelExportRequestRepo> getListClientsInformation() {
        List<ClientEntity> clients = clientRepository.findAll();
        List<ContactEntity> contacts = contactRepository.findAll();
        List<AddressEntity> addresses = addressRepository.findAll();
        List<ContractEntity> contracts = contractRepository.findAll();

        Map<Integer, List<ContactEntity>> contactsByClientId = contacts.stream()
                .collect(Collectors.groupingBy(ContactEntity::getClientId));

        Map<Integer, List<AddressEntity>> addressesByClientId = addresses.stream()
                .collect(Collectors.groupingBy(AddressEntity::getClientId));

        Map<Integer, List<ContractEntity>> contractsByClientId = contracts.stream()
                .collect(Collectors.groupingBy(ContractEntity::getClientId));

        return clients.stream()
                .map(client -> {
                    List<ContactEntity> clientContacts = new ArrayList<>(contactsByClientId.getOrDefault(client.getClientId(), Collections.emptyList()));
                    List<AddressEntity> clientAddresses = new ArrayList<>(addressesByClientId.getOrDefault(client.getClientId(), Collections.emptyList()));
                    List<ContractEntity> clientContracts = new ArrayList<>(contractsByClientId.getOrDefault(client.getClientId(), Collections.emptyList()));

                    ContractEntity latestContract = clientContracts.stream()
                            .max(Comparator.comparing(ContractEntity::getCreateDate))
                            .orElse(null);

                    return new ExcelExportRequestRepo(
                            client.getClientId(),
                            client.getFullName(),
                            client.getIin(),
                            clientContacts.isEmpty() ? null : clientContacts.get(0).getPhoneNumber(),
                            clientContacts.isEmpty() ? null : clientContacts.get(0).getWorkPhone(),
                            clientAddresses.isEmpty() ? null : clientAddresses.get(0).getEmail(),
                            clientAddresses.isEmpty() ? null : clientAddresses.get(0).getResidenceAddress(),
                            clientAddresses.isEmpty() ? null : clientAddresses.get(0).getRegistrationAddress(),
                            latestContract != null ? latestContract.getLoanAmount() : null,
                            latestContract != null ? latestContract.getTotalDue() : null,
                            latestContract != null ? latestContract.getPaymentAmount() : null,
                            latestContract != null ? latestContract.getOverdueAmount() : null,
                            latestContract != null ? latestContract.getPenaltyAmount() : null,
                            latestContract != null ? latestContract.getPaymentDate() : OffsetDateTime.now(),
                            latestContract != null ? latestContract.getOverdueDays() : null,
                            latestContract != null ? latestContract.getRepaymentDate() :  OffsetDateTime.now(),
                            latestContract != null ? latestContract.getProductType() : null,
                            latestContract != null ? latestContract.getProductName() : null,
                            latestContract != null ? latestContract.getContractNumber() : null,
                            latestContract != null ? latestContract.getOpenDate() :  OffsetDateTime.now(),
                            latestContract != null ? latestContract.getEndDate() :  OffsetDateTime.now(),
                            latestContract != null ? latestContract.getIsArc() : null,
                            latestContract != null ? latestContract.getCreateDate() :  OffsetDateTime.now(),
                            latestContract != null ? latestContract.getCompanyId() : null,
                            latestContract != null ? latestContract.getCompanyName() : null
                    );
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<ExcelExportIin> getListClients() {
        List<ClientEntity> clientEntities = clientRepository.findAll();
        List<ContractEntity> contractEntities = contractRepository.findAll();

        List<ExcelExportIin> excelExportIins = new ArrayList<>();

        for (ClientEntity clientEntity : clientEntities) {
            List<ContractEntity> contracts = contractEntities.stream()
                    .filter(contract -> contract.getClientId() == clientEntity.getClientId())
                    .collect(Collectors.toList());

            for (ContractEntity contractEntity : contracts) {
                excelExportIins.add(merge(clientEntity, Collections.singletonList(contractEntity)));
            }
        }

        return excelExportIins;
    }


    private ExcelExportIin merge(ClientEntity clientEntity, List<ContractEntity> contracts) {
        ContractEntity contractEntity = contracts.isEmpty() ? new ContractEntity() : contracts.get(0);
        return ExcelExportIin.builder()
                .clientId(clientEntity.getClientId())
                .fullName(clientEntity.getFullName())
                .iin(clientEntity.getIin())
                .contractNumber(contractEntity.getContractNumber())
                .build();
    }


    public List<ExcelExportRequestRepo> getListBySegment(Integer segmentId) {
        return clientRepository.findListBySegment(segmentsService.getSegmentsById(segmentId));
    }

//    public List<ExcelExportRequestRepo> getListBySegment(Integer segmentId) {
//        List<ExcelExportRequestRepo> res = new ArrayList<>();
//        List<ExcelExportRequestRepo> list = getListClientsInformation();
//        List<SegmentByClientsEntity> segmentList = segmentByClientsRepository.findBySegmentId(segmentId);
//        //val ww = segmentsService.getSegmentsById(segmentId);
//
//        Map<Integer, ExcelExportRequestRepo> excelExportRequestRepoMap = list.stream()
//                .collect(Collectors.toMap(ExcelExportRequestRepo::getClientId, Function.identity()));
//
//        for (SegmentByClientsEntity segmentByClientsEntity : segmentList) {
//            ExcelExportRequestRepo excelExportRequestRepo = excelExportRequestRepoMap.get(segmentByClientsEntity.getClient().getClientId());
//            if (excelExportRequestRepo != null) {
//                res.add(excelExportRequestRepo);
//            }
//        }
//        return res;
//    }

    @Override
    @SneakyThrows
    @Transactional
    public void saveFile(List<ExcelExportRequest> excelExports) {
        for (ExcelExportRequest excelExport : excelExports){
            ClientEntity clientEntity = new ClientEntity();
            clientEntity.setFullName(excelExport.getFio());
            clientEntity.setIin(excelExport.getIin());
            ClientEntity  clientId = clientRepository.save(clientEntity);

            ContactEntity contactEntity = new ContactEntity();
            contactEntity.setPhoneNumber(excelExport.getPhoneNumber());
            contactEntity.setClientId(clientId.getClientId());
            contactEntity.setHomeAddress(excelExport.getRegistrAddress());
            contactEntity.setWorkPhone(excelExport.getPhoneNumber());
            contactRepository.save(contactEntity);

            AddressEntity addressEntity = new AddressEntity();
            addressEntity.setClientId(clientId.getClientId());
            addressEntity.setEmail(excelExport.getEmail());
            addressEntity.setResidenceAddress(excelExport.getResidenceAddress());
            addressEntity.setRegistrationAddress(excelExport.getRegistrAddress());
            addressRepository.save(addressEntity);

            ContractEntity contractEntity = new ContractEntity();
            contractEntity.setContractNumber(excelExport.getContractNumber());
            contractEntity.setOpenDate(excelExport.getContractOpenDate());
            contractEntity.setEndDate(excelExport.getDateEnd());
            contractEntity.setPaymentDate(excelExport.getPaymentDate());
            contractEntity.setPaymentAmount(excelExport.getPaymentAmount());
            contractEntity.setOverdueAmount(excelExport.getOverdueAmount());
            contractEntity.setTotalDue(excelExport.getTotalDebtAmount());
            contractEntity.setOverdueDays(excelExport.getOverdueDay());
            contractEntity.setRepaymentDate(excelExport.getRepaymentDate());
            contractEntity.setClientId(clientId.getClientId());
            contractEntity.setProductType(excelExport.getProductType());
            contractEntity.setProductName(excelExport.getProductName());
            contractEntity.setLoanAmount(excelExport.getLoanAmount());
            contractEntity.setIsArc(excelExport.getIsArc());
            contractEntity.setCreateDate(excelExport.getCreateDate());
            contractEntity.setCompanyId(excelExport.getCompanyId());
            contractEntity.setCompanyName(excelExport.getCompanyName());
            contractRepository.save(contractEntity);
        }
    }

    @Override
    @SneakyThrows
    @Transactional
    public void saveContract(List<ExcelExportRequest> excelExports) {
        for (ExcelExportRequest excelExport : excelExports) {
            ContractEntity contractEntity = new ContractEntity();
            contractEntity.setContractNumber(excelExport.getContractNumber());
            contractEntity.setOpenDate(excelExport.getContractOpenDate());
            contractEntity.setEndDate(excelExport.getDateEnd());
            contractEntity.setPaymentDate(excelExport.getPaymentDate());
            contractEntity.setPaymentAmount(excelExport.getPaymentAmount());
            contractEntity.setOverdueAmount(excelExport.getOverdueAmount());
            contractEntity.setTotalDue(excelExport.getTotalDebtAmount());
            contractEntity.setOverdueDays(excelExport.getOverdueDay());
            contractEntity.setClientId(excelExport.getClientId());
            contractEntity.setRepaymentDate(excelExport.getRepaymentDate());
            contractEntity.setProductType(excelExport.getProductType());
            contractEntity.setProductName(excelExport.getProductName());
            contractEntity.setLoanAmount(excelExport.getLoanAmount());
            contractEntity.setIsArc(excelExport.getIsArc());
            contractEntity.setCreateDate(excelExport.getCreateDate());
            contractEntity.setCompanyId(excelExport.getCompanyId());
            contractEntity.setCompanyName(excelExport.getCompanyName());
            contractRepository.save(contractEntity);
        }
    }
}
