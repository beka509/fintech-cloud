package kz.fintech.apiservice.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kz.fintech.commons.feignclients.DbServiceClient;
import kz.fintech.models.SegmentCreationRequest;
import kz.fintech.models.client.ClientContact;
import kz.fintech.models.dictionary.Channels;
import kz.fintech.models.dictionary.CommFq;
import kz.fintech.models.dictionary.Scripts;
import kz.fintech.models.dictionary.Sequences;
import kz.fintech.models.excel.ExcelExportRequestRepo;
import kz.fintech.models.loaner.*;
import kz.fintech.models.strategy.SegmentByClientsDTO;
import kz.fintech.models.strategy.SegmentDto;
import kz.fintech.models.strategy.StrategyAlgorithmsDto;
import kz.fintech.models.strategy.StrategyDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
@Slf4j
@Api(value = "api", tags = "API для db-service")
public class DbController {


    private final DbServiceClient dbServiceClient;

    public DbController(DbServiceClient dbServiceClient) {
        this.dbServiceClient = dbServiceClient;
    }

    @ApiOperation("")
    @PostMapping(value = "/dictionary/channels/add")
    public Channels createChannels(@RequestBody Channels channels) {
        return dbServiceClient.createChannels(channels);
    }

    @GetMapping("/dictionary/channels/view/{id}")
    Channels getChannelsById(@PathVariable("id") Integer id){
        return dbServiceClient.getChannelsById(id);
    }

    @GetMapping("/dictionary/channels/view/all")
    List<Channels> getAllChannels(){
        return dbServiceClient.getAllChannels();
    }

    @PutMapping("/dictionary/channels/update/{id}")
    Channels updateChannels(@PathVariable("id") Integer id, @RequestBody Channels channels){
        return dbServiceClient.updateChannels(id, channels);
    }

    @DeleteMapping("/dictionary/channels/delete/{id}")
    void deleteChannels(@PathVariable("id") Integer id){
        dbServiceClient.deleteChannels(id);
    }

    @PostMapping("/dictionary/sequences/add")
    Sequences createSequences(@RequestBody Sequences sequences){
        return dbServiceClient.createSequences(sequences);
    }

    @GetMapping("/dictionary/sequences/view/{id}")
    Sequences getSequencesById(@PathVariable("id") Integer id){
        return dbServiceClient.getSequencesById(id);
    }

    @GetMapping("/dictionary/sequences/view/all")
    List<Sequences> getAllSequences(){
        return dbServiceClient.getAllSequences();
    }

    @PutMapping("/dictionary/sequences/update/{id}")
    Sequences updateSequences(@PathVariable("id") Integer id, @RequestBody Sequences sequences){
        return dbServiceClient.updateSequences(id, sequences);
    }

    @DeleteMapping("/dictionary/sequences/delete/{id}")
    void deleteSequences(@PathVariable("id") Integer id){
        dbServiceClient.deleteSequences(id);
    }

    @PostMapping("/dictionary/commfq/add")
    CommFq createCommFq(@RequestBody CommFq commFq){
        return dbServiceClient.createCommFq(commFq);
    }

    @GetMapping("/dictionary/commfq/view/{id}")
    CommFq getCommFqById(@PathVariable("id") Integer id){
        return dbServiceClient.getCommFqById(id);
    }

    @GetMapping("/dictionary/commfq/view/all")
    List<CommFq> getAllCommFq(){
        return dbServiceClient.getAllCommFq();
    }

    @PutMapping("/dictionary/commfq/update/{id}")
    CommFq updateCommFq(@PathVariable("id") Integer id, @RequestBody CommFq commFq){
        return dbServiceClient.updateCommFq(id, commFq);
    }

    @DeleteMapping("/dictionary/commfq/delete/{id}")
    void deleteCommFq(@PathVariable("id") Integer id){
        dbServiceClient.deleteCommFq(id);
    }

    @PostMapping("/dictionary/scripts/add")
    Scripts createScripts(@RequestBody Scripts scripts){
        return dbServiceClient.createScripts(scripts);
    }

    @GetMapping("/dictionary/scripts/view/{id}")
    Scripts getScriptsById(@PathVariable("id") Integer id){
        return dbServiceClient.getScriptsById(id);
    }

    @GetMapping("/dictionary/scripts/view/all")
    List<Scripts> getAllScripts(){
        return dbServiceClient.getAllScripts();
    }

    @PutMapping("/dictionary/scripts/update/{id}")
    Scripts updateScripts(@PathVariable("id") Integer id, @RequestBody Scripts scripts){
        return dbServiceClient.updateScripts(id, scripts);
    }

    @DeleteMapping("/dictionary/scripts/delete/{id}")
    void deleteScripts(@PathVariable("id") Integer id){
        dbServiceClient.deleteScripts(id);
    }

    ///////////////////////////////////////////////////// SegmentController
    @PostMapping("/segment/add")
    SegmentDto createSegment(@RequestBody SegmentDto segmentsDTO){
        return dbServiceClient.createSegment(segmentsDTO);
    }

    @GetMapping("/segment/view/{id}")
    SegmentDto getSegmentById(@PathVariable("id") Integer id){
        return dbServiceClient.getSegmentById(id);
    }

    @GetMapping("/segment/view/all")
    List<SegmentDto> getAllSegments(){
        return dbServiceClient.getAllSegments();
    }

    @PutMapping("/segment/update/{id}")
    SegmentDto updateSegment(@PathVariable("id") Integer id, @RequestBody SegmentDto segmentDto){
        return dbServiceClient.updateSegment(id, segmentDto);
    }

    @DeleteMapping("/segment/delete/{id}")
    void deleteSegment(@PathVariable("id") Integer id){
        dbServiceClient.deleteSegment(id);
    }

    @PostMapping("/segment/segment-by-clients/add")
    SegmentByClientsDTO createSegmentByClients(@RequestBody SegmentByClientsDTO segmentByClientsDTO){
        return dbServiceClient.createSegmentByClients(segmentByClientsDTO);
    }

    @GetMapping("/segment/segment-by-clients/view/{id}")
    SegmentByClientsDTO getSegmentByClientsById(@PathVariable("id") Integer id){
        return dbServiceClient.getSegmentByClientsById(id);
    }

    @GetMapping("/segment/segment-by-clients/view/all")
    List<SegmentByClientsDTO> getAllSegmentByClients(){
        return dbServiceClient.getAllSegmentByClients();
    }

    @PutMapping("/segment/segment-by-clients/update/{id}")
    SegmentByClientsDTO updateSegmentByClients(@PathVariable("id") Integer id,
                                               @RequestBody SegmentByClientsDTO updatedSegmentByClientsDTO){
        return dbServiceClient.updateSegmentByClients(id, updatedSegmentByClientsDTO);
    }

    @DeleteMapping("/segment/segment-by-clients/delete/{id}")
    void deleteSegmentByClients(@PathVariable("id") Integer id){
        dbServiceClient.deleteSegmentByClients(id);
    }

    @PostMapping("/segment/segment-by-clients/add-clients/{segmentId}")
    void addClientsToSegment(@PathVariable("segmentId") Integer segmentId,
                             @RequestBody List<Integer> clientIds){
        dbServiceClient.addClientsToSegment(segmentId, clientIds);
    }

    @DeleteMapping("/segment/segment-by-clients/delete/by-segment/{segmentId}")
    void deleteSegmentByClientsBySegmentId(@PathVariable("segmentId") Integer segmentId){
        dbServiceClient.deleteSegmentByClientsBySegmentId(segmentId);
    }

    @PostMapping("/segment/create-and-add-clients")
    void createSegmentAndAddClients(@RequestBody SegmentCreationRequest request){
        dbServiceClient.createSegmentAndAddClients(request);
    }

    @GetMapping(value = "/getListClientsInformation")
    public List<ExcelExportRequestRepo> getListClientsInformation() {
        log.info("------");
        return dbServiceClient.getListClientsInformation();
    }

    @GetMapping("/getList/by-segment/{segmentId}")
    public List<ExcelExportRequestRepo> findListBySegment(@PathVariable("segmentId") Integer segmentId) {
        return dbServiceClient.findListBySegment(segmentId);
    }



    ///////////////////////////////////////////////////////////////////// StrategyController
    @PostMapping("/strategy/add")
    StrategyDto createStrategy(@RequestBody StrategyDto strategyDto){
        return dbServiceClient.createStrategy(strategyDto);
    }

    @GetMapping("/strategy/view/{id}")
    StrategyDto getStrategyById(@PathVariable("id") Integer id){
        return dbServiceClient.getStrategyById(id);
    }

    @GetMapping("/strategy/view/all")
    List<StrategyDto> getAllStrategies(){
        return dbServiceClient.getAllStrategies();
    }

    @PutMapping("/strategy/update/{id}")
    StrategyDto updateStrategy(@PathVariable("id") Integer id, @RequestBody StrategyDto strategyDto){
        return dbServiceClient.updateStrategy(id, strategyDto);
    }

    @DeleteMapping("/strategy/delete/{id}")
    void deleteStrategy(@PathVariable("id") Integer id){
        dbServiceClient.deleteStrategy(id);
    }

    @PostMapping("/strategy/strategy-algrm/add")
    StrategyAlgorithmsDto createStrategyAlgorithm(@RequestBody StrategyAlgorithmsDto strategyAlgorithm){
        return dbServiceClient.createStrategyAlgorithm(strategyAlgorithm);
    }

    @GetMapping("/strategy/strategy-algrm/view/{id}")
    StrategyAlgorithmsDto getStrategyAlgorithmById(@PathVariable("id") Integer id){
        return dbServiceClient.getStrategyAlgorithmById(id);
    }

    @GetMapping("/strategy/strategy-algrm/view/all")
    List<StrategyAlgorithmsDto> getAllStrategyAlgorithms(){
        return dbServiceClient.getAllStrategyAlgorithms();
    }

    @PutMapping("/strategy/strategy-algrm/update/{id}")
    StrategyAlgorithmsDto updateStrategyAlgorithm(@PathVariable("id") Integer id, @RequestBody StrategyAlgorithmsDto strategyAlgorithm){
        return dbServiceClient.updateStrategyAlgorithm(id, strategyAlgorithm);
    }

    @DeleteMapping("/strategy/strategy-algrm/delete/{id}")
    void deleteStrategyAlgorithm(@PathVariable("id") Integer id){
        dbServiceClient.deleteStrategyAlgorithm(id);
    }

    @GetMapping("/strategy/strategy-algrm/view-by-strategy/{id}")
    List<StrategyAlgorithmsDto> getAllStrategyAlgorithms(@PathVariable("id") Integer id){
        return dbServiceClient.getAllStrategyAlgorithms(id);
    }

    ////////////////////////////////////////////////////////////////////// LoanerController
    @GetMapping("/loaner/view/{loanerId}")
    LoanerDto getLoaner(@PathVariable("loanerId") Integer loanerId){
        return dbServiceClient.getLoaner(loanerId);
    }

    @GetMapping("/loaner/view/all")
    List<LoanerDto> getAllLoaners(){
        return dbServiceClient.getAllLoaners();
    }

    @PostMapping("/loaner/add")
    LoanerDto createLoaner(@RequestBody LoanerDto loanerDTO){
        return dbServiceClient.createLoaner(loanerDTO);
    }

    @PutMapping("/loaner/update/{loanerId}")
    LoanerDto updateLoaner(@PathVariable("loanerId") Integer loanerId,
                           @RequestBody LoanerDto loanerDto){
        return dbServiceClient.updateLoaner(loanerId, loanerDto);
    }

    @DeleteMapping("/loaner/delete/{loanerId}")
    void deleteLoaner(@PathVariable("loanerId") Integer loanerId){
        dbServiceClient.deleteLoaner(loanerId);
    }

    @GetMapping("/loaner/contact/view/{contactId}")
    LoanerContactDto getLoanerContact(@PathVariable("contactId") Integer contactId){
        return dbServiceClient.getLoanerContact(contactId);
    }

    @GetMapping("/loaner/contact/view/all")
    List<LoanerContactDto> getAllLoanerContacts(){
        return dbServiceClient.getAllLoanerContacts();
    }

    @GetMapping("/loaner/contact/by-loaner/{loanerId}")
    List<LoanerContactDto> getLoanerContactsByLoanerId(@PathVariable("loanerId") Integer loanerId){
        return dbServiceClient.getLoanerContactsByLoanerId(loanerId);
    }

    @PostMapping("/loaner/contact/add")
    LoanerContactDto createLoanerContact(@RequestBody LoanerContactDto loanerContactDto){
        return dbServiceClient.createLoanerContact(loanerContactDto);
    }

    @PutMapping("/loaner/contact/update/{contactId}")
    LoanerContactDto updateLoanerContact(@PathVariable("contactId") Integer contactId,
                                         @RequestBody LoanerContactDto loanerContactDto){
        return dbServiceClient.updateLoanerContact(contactId, loanerContactDto);
    }

    @DeleteMapping("/loaner/contact/delete/{contactId}")
    void deleteLoanerContact(@PathVariable("contactId") Integer contactId){
        dbServiceClient.deleteLoanerContact(contactId);
    }

    @GetMapping("/loaner/address/view/{addressId}")
    LoanerAddressDto getLoanerAddress(@PathVariable("addressId") Integer addressId){
        return dbServiceClient.getLoanerAddress(addressId);
    }

    @GetMapping("/loaner/address/view/all")
    List<LoanerAddressDto> getAllLoanerAddresses(){
        return dbServiceClient.getAllLoanerAddresses();
    }

    @PostMapping("/loaner/address/add")
    LoanerAddressDto createLoanerAddress(@RequestBody LoanerAddressDto loanerAddressDto){
        return dbServiceClient.createLoanerAddress(loanerAddressDto);
    }

    @PutMapping("/loaner/address/update/{addressId}")
    LoanerAddressDto updateLoanerAddress(@PathVariable("addressId") Integer addressId,
                                         @RequestBody LoanerAddressDto loanerAddressDto){
        return dbServiceClient.updateLoanerAddress(addressId, loanerAddressDto);
    }

    @DeleteMapping("/loaner/address/delete/{addressId}")
    void deleteLoanerAddress(@PathVariable("addressId") Integer addressId){
        dbServiceClient.deleteLoanerAddress(addressId);
    }
    @GetMapping("/loaner/address/by-loaner/{loanerId}")
    public List<LoanerAddressDto> getAddressByLoanerId(@PathVariable("loanerId") Integer loanerId) {
        return dbServiceClient.getAddressByLoanerId(loanerId);
    }
    //LoanerCallResultDemo
    @GetMapping("/loaner/loanerCallResultDemo/view/{id}")
    public LoanerCallResultDemoDto getLoanerCallResultDemoById(@PathVariable("id") Integer id) {
        return dbServiceClient.getLoanerCallResultDemoById(id);
    }


    @GetMapping("/loanerCallResultDemo/view/all")
    public List<LoanerCallResultDemoDto> getAllLoanerCallResultDemos() {
        return dbServiceClient.getAllLoanerCallResultDemos();
    }

    @PostMapping("/loaner/loanerCallResultDemo/add")
    public LoanerCallResultDemoDto saveLoanerCallResultDemo(@RequestBody LoanerCallResultDemoDto dto) {
        return dbServiceClient.saveLoanerCallResultDemo(dto);
    }

    @PutMapping("/loaner/loanerCallResultDemo/update/{id}")
    public LoanerCallResultDemoDto updateLoanerCallResultDemo(
            @PathVariable("id") Integer id,
            @RequestBody LoanerCallResultDemoDto dto
    ) {
        return dbServiceClient.updateLoanerCallResultDemo(id,dto);
    }

    @DeleteMapping("/loanerCallResultDemo/delete/{id}")
    public void deleteLoanerCallResultDemo(@PathVariable("id") Integer id) {
        dbServiceClient.deleteLoanerCallResultDemo(id);
    }

    @GetMapping("/loaner/loanerCallResultDemo/by-loaner/{loanerId}")
    public List<LoanerCallResultDemoDto> getLoanerCallResultDemosByLoanerId(@PathVariable("loanerId") Integer loanerId) {
        return dbServiceClient.getLoanerCallResultDemosByLoanerId(loanerId);
    }


    @PostMapping("/client/contacts/add")
    public ClientContact createContact(@RequestBody ClientContact contact) {
        return dbServiceClient.createContact(contact);
    }

    // Получение контакта по его ID
    @GetMapping("/client/contacts/view/{contactId}")
    public ClientContact getContactById(@PathVariable("contactId") Integer contactId) {
        return dbServiceClient.getContactById(contactId);
    }

    // Получение контактов по clientId
    @GetMapping("/contacts/client/{clientId}")
    public List<ClientContact> getContactsByClientId(@PathVariable("clientId") Integer clientId) {
        return  dbServiceClient.getContactsByClientId(clientId);
    }

    // Обновление существующего контакта
    @PutMapping("/contacts/update/{contactId}")
    public ClientContact updateContact(@PathVariable("contactId") Integer contactId, @RequestBody ClientContact updatedContact) {
        return dbServiceClient.updateContact(contactId, updatedContact);
    }

    // Удаление контакта по его ID
    @DeleteMapping("/contacts/delete/{contactId}")
    public boolean deleteContact(@PathVariable("contactId") Integer contactId) {
        return dbServiceClient.deleteContact(contactId);
    }

    @PostMapping("/loaner-comm-history/add")
    public LoanerCommHistoryDto addLoanerCommHistory(@RequestBody LoanerCommHistoryDto loanerCommHistoryDto) {
        return dbServiceClient.addLoanerCommHistory(loanerCommHistoryDto);
    }

    @GetMapping("/loaner-comm-history/view/all")
    public List<LoanerCommHistoryDto> getAllLoanerCommHistory() {
        return dbServiceClient.getAllLoanerCommHistory();
    }

    @GetMapping("/loaner-comm-history/by-client-id/{clientId}")
    public List<LoanerCommHistoryDto> getLoanerCommHistoryByClientId(@PathVariable("clientId") Integer clientId) {
        return dbServiceClient.getLoanerCommHistoryByClientId(clientId);
    }

    @GetMapping("/client/search")
    public List<ExcelExportRequestRepo> searchClients(@RequestParam("iin") String iin, @RequestParam("phoneNumber") String phoneNumber, @RequestParam("contractNumber") String contractNumber, @RequestParam("fio") String fio){
        return dbServiceClient.searchClients(iin,phoneNumber,contractNumber,fio);
    }

    @GetMapping("/client/get-client-by-id/{clientId}")
    public ExcelExportRequestRepo getClientById(@PathVariable("clientId") Integer clientId) {
        return dbServiceClient.getClientById(clientId);
    }


}