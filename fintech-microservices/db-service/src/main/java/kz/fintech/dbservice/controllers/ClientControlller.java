package kz.fintech.dbservice.controllers;

import kz.fintech.dbservice.entities.ContactEntity;
import kz.fintech.dbservice.services.ClientService;
import kz.fintech.models.client.ClientContact;
import kz.fintech.models.excel.ExcelExportRequestRepo;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/client")
public class ClientControlller {

    private ClientService clientService;



    @GetMapping("/search")
    public List<ExcelExportRequestRepo> searchClients(@RequestParam String iin, @RequestParam String phoneNumber, @RequestParam String contractNumber, @RequestParam String fio) {
        return clientService.searchClients(iin, phoneNumber, contractNumber, fio);
    }

    @GetMapping("/get-client-by-id/{clientId}")
    public ExcelExportRequestRepo getClientById(@PathVariable("clientId") Integer clientId) {
        return clientService.getClientById(clientId);
    }

    // Добавление нового контакта
    @PostMapping("/contacts/add")
    public ClientContact createContact(@RequestBody ClientContact contact) {
        return convertToModel(clientService.createContact(convertToEntity(contact)));
    }

    // Получение контакта по его ID
    @GetMapping("/contacts/view/{contactId}")
    public ClientContact getContactById(@PathVariable("contactId") Integer contactId) {
        return convertToModel(clientService.getContactById(contactId));
    }

    // Получение контактов по clientId
    @GetMapping("/contacts/client/{clientId}")
    public List<ClientContact> getContactsByClientId(@PathVariable("clientId") Integer clientId) {

        List<ContactEntity> entities = clientService.getContactsByClientId(clientId);
        return entities.stream()
                .map(this::convertToModel)
                .collect(Collectors.toList());
    }

    // Обновление существующего контакта
    @PutMapping("/contacts/update/{contactId}")
    public ClientContact updateContact(@PathVariable("contactId") Integer contactId, @RequestBody ClientContact updatedContact) {
        return convertToModel(clientService.updateContact(contactId, convertToEntity(updatedContact)));
    }

    // Удаление контакта по его ID
    @DeleteMapping("/contacts/delete/{contactId}")
    public boolean deleteContact(@PathVariable("contactId") Integer contactId) {
        return clientService.deleteContact(contactId);
    }

    private ContactEntity convertToEntity(ClientContact clientContact) {
        ContactEntity entity = new ContactEntity();
        entity.setContactId(clientContact.getContactId() == null ? 0 :clientContact.getContactId()  );
        entity.setClientId(clientContact.getClientId());
        entity.setHomeAddress(clientContact.getHomeAddress());
        entity.setPhoneNumber(clientContact.getPhoneNumber());
        entity.setTelecomOperator(clientContact.getTelecomOperator());
        entity.setVerified(clientContact.isVerified());
        entity.setWorkPhone(clientContact.getWorkPhone());
        entity.setTypeName(clientContact.getTypeName());
//        entity.setContactFio(clientContact.getContactFio());
//        entity.setChangeDate(clientContact.getChangeDate());
        return entity;
    }

    private ClientContact convertToModel(ContactEntity entity) {
        ClientContact clientContact = new ClientContact();
        clientContact.setClientId(entity.getClientId());
        clientContact.setContactId(entity.getContactId());
        clientContact.setHomeAddress(entity.getHomeAddress());
        clientContact.setPhoneNumber(entity.getPhoneNumber());
        clientContact.setTelecomOperator(entity.getTelecomOperator());
        clientContact.setVerified(entity.isVerified());
        clientContact.setWorkPhone(entity.getWorkPhone());
        clientContact.setTypeName(entity.getTypeName());
//        clientContact.setContactFio(entity.getContactFio());
        clientContact.setChangeDate(entity.getChangeDate());

        return clientContact;
    }
}
