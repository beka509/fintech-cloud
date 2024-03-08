package kz.fintech.commons.feignclients;

import kz.fintech.models.SegmentCreationRequest;
import kz.fintech.models.auth.RefreshTokenDto;
import kz.fintech.models.auth.UsersDto;
import kz.fintech.models.client.ClientContact;
import kz.fintech.models.dictionary.Channels;
import kz.fintech.models.dictionary.CommFq;
import kz.fintech.models.dictionary.Scripts;
import kz.fintech.models.dictionary.Sequences;
import kz.fintech.models.excel.ExcelExportIin;
import kz.fintech.models.excel.ExcelExportRequest;
import kz.fintech.models.excel.ExcelExportRequestRepo;
import kz.fintech.models.loaner.*;
import kz.fintech.models.strategy.SegmentByClientsDTO;
import kz.fintech.models.strategy.SegmentDto;
import kz.fintech.models.strategy.StrategyAlgorithmsDto;
import kz.fintech.models.strategy.StrategyDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@FeignClient(name = ServiceNames.DB_SERVICE, url = ServiceUrls.DB_SERVICE)
public interface DbServiceClient {

    ////////////////////////////////////////////////////////////// FileController
    @PostMapping("/fintech/file/excel/saveFile")
    void saveFile(List<ExcelExportRequest> excelExport);

    @PostMapping("/fintech/file/excel/saveContract")
    void saveContract(List<ExcelExportRequest> excelExport);

    @PostMapping("/fintech/file/excel/sendFtp")
    List<ExcelExportRequestRepo> sendExcelFtp();

    @GetMapping("/fintech/getListClientsInformation")
    List<ExcelExportRequestRepo> getListClientsInformation();

    @GetMapping("/fintech/getListClients")
    List<ExcelExportIin> getListClients();

    @GetMapping("/fintech/getList/by-segment/{segmentId}")
    List<ExcelExportRequestRepo> findListBySegment(@PathVariable("segmentId") Integer segmentId);

    ////////////////////////////////////////////////////////////////// DictionaryController
    @PostMapping("/dictionary/channels/add")
    Channels createChannels(@RequestBody Channels channels);

    @GetMapping("/dictionary/channels/view/{id}")
    Channels getChannelsById(@PathVariable("id") Integer id);

    @GetMapping("/dictionary/channels/view/all")
    List<Channels> getAllChannels();

    @PutMapping("/dictionary/channels/update/{id}")
    Channels updateChannels(@PathVariable("id") Integer id, @RequestBody Channels channels);

    @DeleteMapping("/dictionary/channels/delete/{id}")
    void deleteChannels(@PathVariable("id") Integer id);

    @PostMapping("/dictionary/sequences/add")
    Sequences createSequences(@RequestBody Sequences sequences);

    @GetMapping("/dictionary/sequences/view/{id}")
    Sequences getSequencesById(@PathVariable("id") Integer id);

    @GetMapping("/dictionary/sequences/view/all")
    List<Sequences> getAllSequences();

    @PutMapping("/dictionary/sequences/update/{id}")
    Sequences updateSequences(@PathVariable("id") Integer id, @RequestBody Sequences sequences);

    @DeleteMapping("/dictionary/sequences/delete/{id}")
    void deleteSequences(@PathVariable("id") Integer id);

    @PostMapping("/dictionary/commfq/add")
    CommFq createCommFq(@RequestBody CommFq commFq);

    @GetMapping("/dictionary/commfq/view/{id}")
    CommFq getCommFqById(@PathVariable("id") Integer id);

    @GetMapping("/dictionary/commfq/view/all")
    List<CommFq> getAllCommFq();

    @PutMapping("/dictionary/commfq/update/{id}")
    CommFq updateCommFq(@PathVariable("id") Integer id, @RequestBody CommFq commFq);

    @DeleteMapping("/dictionary/commfq/delete/{id}")
    void deleteCommFq(@PathVariable("id") Integer id);

    @PostMapping("/dictionary/scripts/add")
    Scripts createScripts(@RequestBody Scripts scripts);

    @GetMapping("/dictionary/scripts/view/{id}")
    Scripts getScriptsById(@PathVariable("id") Integer id);

    @GetMapping("/dictionary/scripts/view/all")
    List<Scripts> getAllScripts();

    @PutMapping("/dictionary/scripts/update/{id}")
    Scripts updateScripts(@PathVariable("id") Integer id, @RequestBody Scripts scripts);

    @DeleteMapping("/dictionary/scripts/delete/{id}")
    void deleteScripts(@PathVariable("id") Integer id);

    ///////////////////////////////////////////////////// SegmentController
    @PostMapping("/segment/add")
    SegmentDto createSegment(@RequestBody SegmentDto segmentsDTO);

    @GetMapping("/segment/view/{id}")
    SegmentDto getSegmentById(@PathVariable("id") Integer id);

    @GetMapping("/segment/view/all")
    List<SegmentDto> getAllSegments();

    @PutMapping("/segment/update/{id}")
    SegmentDto updateSegment(@PathVariable("id") Integer id, @RequestBody SegmentDto segmentDto);

    @DeleteMapping("/segment/delete/{id}")
    void deleteSegment(@PathVariable("id") Integer id);

    @PostMapping("/segment/segment-by-clients/add")
    SegmentByClientsDTO createSegmentByClients(@RequestBody SegmentByClientsDTO segmentByClientsDTO);

    @GetMapping("/segment/segment-by-clients/view/{id}")
    SegmentByClientsDTO getSegmentByClientsById(@PathVariable("id") Integer id);

    @GetMapping("/segment/segment-by-clients/view/all")
    List<SegmentByClientsDTO> getAllSegmentByClients();

    @PutMapping("/segment/segment-by-clients/update/{id}")
    SegmentByClientsDTO updateSegmentByClients(@PathVariable("id") Integer id,
                                               @RequestBody SegmentByClientsDTO updatedSegmentByClientsDTO);

    @DeleteMapping("/segment/segment-by-clients/delete/{id}")
    void deleteSegmentByClients(@PathVariable("id") Integer id);

    @PostMapping("/segment/segment-by-clients/add-clients/{segmentId}")
    void addClientsToSegment(@PathVariable("segmentId") Integer segmentId,
                             @RequestBody List<Integer> clientIds);

    @DeleteMapping("/segment/segment-by-clients/delete/by-segment/{segmentId}")
    void deleteSegmentByClientsBySegmentId(@PathVariable("segmentId") Integer segmentId);

    @PostMapping("/segment/create-and-add-clients")
    void createSegmentAndAddClients(@RequestBody SegmentCreationRequest request);

    ///////////////////////////////////////////////////////////////////// StrategyController
    @PostMapping("/strategy/add")
    StrategyDto createStrategy(@RequestBody StrategyDto strategyDto);

    @GetMapping("/strategy/view/{id}")
    StrategyDto getStrategyById(@PathVariable("id") Integer id);

    @GetMapping("/strategy/view/all")
    List<StrategyDto> getAllStrategies();

    @PutMapping("/strategy/update/{id}")
    StrategyDto updateStrategy(@PathVariable("id") Integer id, @RequestBody StrategyDto strategyDto);

    @DeleteMapping("/strategy/delete/{id}")
    void deleteStrategy(@PathVariable("id") Integer id);

    @PostMapping("/strategy/strategy-algrm/add")
    StrategyAlgorithmsDto createStrategyAlgorithm(@RequestBody StrategyAlgorithmsDto strategyAlgorithm);

    @GetMapping("/strategy/strategy-algrm/view/{id}")
    StrategyAlgorithmsDto getStrategyAlgorithmById(@PathVariable("id") Integer id);

    @GetMapping("/strategy/strategy-algrm/view/all")
    List<StrategyAlgorithmsDto> getAllStrategyAlgorithms();

    @PutMapping("/strategy/strategy-algrm/update/{id}")
    StrategyAlgorithmsDto updateStrategyAlgorithm(@PathVariable("id") Integer id, @RequestBody StrategyAlgorithmsDto strategyAlgorithm);

    @DeleteMapping("/strategy/strategy-algrm/delete/{id}")
    void deleteStrategyAlgorithm(@PathVariable("id") Integer id);

    @GetMapping("/strategy/strategy-algrm/view-by-strategy/{id}")
    List<StrategyAlgorithmsDto> getAllStrategyAlgorithms(@PathVariable("id") Integer id);

    ////////////////////////////////////////////////////////////////////// LoanerController
    @GetMapping("/loaner/view/{loanerId}")
    LoanerDto getLoaner(@PathVariable("loanerId") Integer loanerId);

    @GetMapping("/loaner/view/all")
    List<LoanerDto> getAllLoaners();

    @PostMapping("/loaner/add")
    LoanerDto createLoaner(@RequestBody LoanerDto loanerDTO);

    @PutMapping("/loaner/update/{loanerId}")
    LoanerDto updateLoaner(@PathVariable("loanerId") Integer loanerId,
                           @RequestBody LoanerDto loanerDto);

    @DeleteMapping("/loaner/delete/{loanerId}")
    void deleteLoaner(@PathVariable("loanerId") Integer loanerId);

    @GetMapping("/loaner/contact/view/{contactId}")
    LoanerContactDto getLoanerContact(@PathVariable("contactId") Integer contactId);

    @GetMapping("/loaner/contact/view/all")
    List<LoanerContactDto> getAllLoanerContacts();

    @GetMapping("/loaner/contact/by-loaner/{loanerId}")
    List<LoanerContactDto> getLoanerContactsByLoanerId(@PathVariable("loanerId") Integer loanerId);

    @PostMapping("/loaner/contact/add")
    LoanerContactDto createLoanerContact(@RequestBody LoanerContactDto loanerContactDto);

    @PutMapping("/loaner/contact/update/{contactId}")
    LoanerContactDto updateLoanerContact(@PathVariable("contactId") Integer contactId,
                                         @RequestBody LoanerContactDto loanerContactDto);

    @DeleteMapping("/loaner/contact/delete/{contactId}")
    void deleteLoanerContact(@PathVariable("contactId") Integer contactId);

    @GetMapping("/loaner/address/view/{addressId}")
    LoanerAddressDto getLoanerAddress(@PathVariable("addressId") Integer addressId);

    @GetMapping("/loaner/address/view/all")
    List<LoanerAddressDto> getAllLoanerAddresses();

    @PostMapping("/loaner/address/add")
    LoanerAddressDto createLoanerAddress(@RequestBody LoanerAddressDto loanerAddressDto);

    @PutMapping("/loaner/address/update/{addressId}")
    LoanerAddressDto updateLoanerAddress(@PathVariable("addressId") Integer addressId,
                                         @RequestBody LoanerAddressDto loanerAddressDto);

    @DeleteMapping("/loaner/address/delete/{addressId}")
    void deleteLoanerAddress(@PathVariable("addressId") Integer addressId);


    @GetMapping("/loaner/address/by-loaner/{loanerId}")
    List<LoanerAddressDto> getAddressByLoanerId(@PathVariable("loanerId") Integer loanerId);

    //LoanerCallResultDemo
    @GetMapping(value="/loaner/loanerCallResultDemo/view/{id}")
    LoanerCallResultDemoDto getLoanerCallResultDemoById(@PathVariable(value = "id") Integer id);



    @GetMapping("/loaner/loanerCallResultDemo/view/all")
    List<LoanerCallResultDemoDto> getAllLoanerCallResultDemos();

    @PostMapping("/loaner/loanerCallResultDemo/add")
    LoanerCallResultDemoDto saveLoanerCallResultDemo(@RequestBody LoanerCallResultDemoDto dto);


    @PutMapping("/loaner/loanerCallResultDemo/update/{id}")
    LoanerCallResultDemoDto updateLoanerCallResultDemo(
            @PathVariable(value = "id") Integer id,
            @RequestBody LoanerCallResultDemoDto dto
    );

    @DeleteMapping("/loaner/loanerCallResultDemo/delete/{id}")
    void deleteLoanerCallResultDemo(@PathVariable(value = "id") Integer id);

    @GetMapping("/loaner/loanerCallResultDemo/by-client/{clientId}")
    List<LoanerCallResultDemoDto> getLoanerCallResultDemosByLoanerId(@PathVariable(value = "clientId") Integer clientId);



    // Contacts

    // Добавление нового контакта
    @PostMapping("/client/contacts/add")
    ClientContact createContact(@RequestBody ClientContact contact);


    // Получение контакта по его ID
    @GetMapping("/client/contacts/view/{contactId}")
    ClientContact getContactById(@PathVariable(value = "contactId") Integer contactId);


    // Получение контактов по clientId
    @GetMapping("/client/contacts/client/{clientId}")
    List<ClientContact> getContactsByClientId(@PathVariable(value = "clientId") Integer clientId);

    // Обновление существующего контакта
    @PutMapping("/client/contacts/update/{contactId}")
    ClientContact updateContact(@PathVariable(value = "contactId") Integer contactId, @RequestBody ClientContact updatedContact);


    // Удаление контакта по его ID
    @DeleteMapping("/client/contacts/delete/{contactId}")
    boolean deleteContact(@PathVariable(value = "contactId") Integer contactId);

    //---------

    // История телекоммуникации

    @PostMapping("/loaner/loaner-comm-history/add")
     LoanerCommHistoryDto addLoanerCommHistory(@RequestBody LoanerCommHistoryDto loanerCommHistoryDto);

    @GetMapping("/loaner/loaner-comm-history/view/all")
    List<LoanerCommHistoryDto> getAllLoanerCommHistory();

    @GetMapping("/loaner/loaner-comm-history/by-client-id/{clientId}")
    List<LoanerCommHistoryDto> getLoanerCommHistoryByClientId(@PathVariable(value = "clientId") Integer clientId);

    @GetMapping("/client/search")
    List<ExcelExportRequestRepo> searchClients(@RequestParam("iin") String iin, @RequestParam("phoneNumber") String phoneNumber, @RequestParam("contractNumber") String contractNumber, @RequestParam("fio") String fio);

    @GetMapping("/client/get-client-by-id/{clientId}")
    ExcelExportRequestRepo getClientById(@PathVariable(value = "clientId") Integer clientId);


    /**
     * Получение данных по пользователю и авторизация
     **/
    @GetMapping("/auth/{id}")
    UsersDto findFirstById(@PathVariable("id") long id);

    @PostMapping("/auth/save")
    UsersDto save(@RequestBody UsersDto usersDto);

    @GetMapping("/auth/username/{username}")
    UsersDto findByUsername(@PathVariable("username") String username);

    @GetMapping("/auth/all")
    List<UsersDto> findAll();

    @PostMapping("/auth/token/refresh")
    RefreshTokenDto refreshToken(@RequestBody UsersDto usersDto);

    @PostMapping("/auth/token/save")
    RefreshTokenDto saveToken(@RequestBody RefreshTokenDto refreshTokenDto);

    @DeleteMapping("/auth/token")
    void delete(@RequestBody RefreshTokenDto refreshTokenDto);

    @GetMapping("/auth/token/{token}")
    Optional<RefreshTokenDto> findByToken(@PathVariable("token") String token);

    @PostMapping("/auth/verifyExpiration")
    RefreshTokenDto verifyExpiration(@RequestBody RefreshTokenDto refreshTokenDto);
}
