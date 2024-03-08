package kz.fintech.dbservice.services.impl;

import kz.fintech.dbservice.entities.ContactEntity;
import kz.fintech.dbservice.repos.ClientContactRepository;
import kz.fintech.dbservice.services.ClientService;
import kz.fintech.dbservice.services.FileService;
import kz.fintech.models.excel.ExcelExportRequestRepo;
import lombok.AllArgsConstructor;
import lombok.val;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private FileService fileService;

    private ClientContactRepository clientContactRepository;

    @Override
    public List<ExcelExportRequestRepo> searchClients(String iin, String phoneNumber, String contractNumber, String fio) {
        val clientList =fileService.getListClientsInformation();
        return clientList.stream()
                .filter(client -> (StringUtils.isEmpty(iin) || client.getIin().contains(iin))
                        && (StringUtils.isEmpty(phoneNumber) || client.getPhoneNumber().contains(phoneNumber))
                        && (StringUtils.isEmpty(contractNumber) || client.getContractNumber().contains(contractNumber))
                        && (StringUtils.isEmpty(fio) || client.getFio().contains(fio)))
                .collect(Collectors.toList());
    }

    @Override
    public ExcelExportRequestRepo getClientById(Integer id) {
        val clientList = fileService.getListClientsInformation();
        return clientList.stream()
                .filter(client -> client.getClientId() != null && client.getClientId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Создание нового контакта
    @Override
    public ContactEntity createContact(ContactEntity contact) {
        return clientContactRepository.save(contact);
    }

    // Получение контакта по его ID
    @Override
    public ContactEntity getContactById(Integer contactId) {
        return clientContactRepository.findById(contactId).orElse(null);
    }

    // Получение контактов по clientId
    public List<ContactEntity> getContactsByClientId(Integer clientId) {
        return clientContactRepository.findByClientId(clientId);
    }

    // Обновление существующего контакта
    @Override
    public ContactEntity updateContact(Integer contactId, ContactEntity updatedContact) {
        if (clientContactRepository.existsById(contactId)) {
            updatedContact.setContactId(contactId);
            return clientContactRepository.save(updatedContact);
        } else {
            return null;
        }
    }

    // Удаление контакта по его ID
    @Override
    public boolean deleteContact(Integer contactId) {
        if (clientContactRepository.existsById(contactId)) {
            clientContactRepository.deleteById(contactId);
            return true;
        } else {
            return false;
        }
    }


}
