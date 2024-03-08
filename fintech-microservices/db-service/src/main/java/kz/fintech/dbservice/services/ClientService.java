package kz.fintech.dbservice.services;

import kz.fintech.dbservice.entities.ContactEntity;
import kz.fintech.models.excel.ExcelExportRequestRepo;

import java.util.List;

public interface ClientService {

    List<ExcelExportRequestRepo> searchClients(String iin, String phoneNumber, String contractNumber, String fio);
    ExcelExportRequestRepo getClientById(Integer id);
    ContactEntity createContact(ContactEntity contact);
    ContactEntity getContactById(Integer contactId);
    List<ContactEntity> getContactsByClientId(Integer clientId);
    ContactEntity updateContact(Integer contactId, ContactEntity updatedContact);
    boolean deleteContact(Integer contactId);

}
