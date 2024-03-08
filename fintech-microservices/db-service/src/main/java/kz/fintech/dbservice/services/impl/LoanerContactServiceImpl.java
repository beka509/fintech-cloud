package kz.fintech.dbservice.services.impl;

import kz.fintech.dbservice.entities.LoanerContactEntity;
import kz.fintech.dbservice.entities.LoanerEntity;
import kz.fintech.dbservice.repos.LoanerContactRepository;
import kz.fintech.dbservice.repos.LoanerRepository;
import kz.fintech.dbservice.services.LoanerContactService;
import kz.fintech.models.loaner.LoanerContactDto;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LoanerContactServiceImpl implements LoanerContactService {
    private final LoanerContactRepository loanerContactRepository;
    private final LoanerRepository loanerRepository;

    @Override
    public LoanerContactDto getLoanerContactById(Integer contactId) {
        LoanerContactEntity contactEntity = loanerContactRepository.findById(contactId)
                .orElseThrow(() -> new RuntimeException("LoanerContact not found with ID: " + contactId));

        return convertToDto(contactEntity);
    }

    @Override
    public List<LoanerContactDto> getAllLoanerContacts() {
        List<LoanerContactEntity> contactEntities = loanerContactRepository.findAll();
        return contactEntities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<LoanerContactDto> getLoanerContactByLoanerId(Integer loanerId) {
        List<LoanerContactEntity> contactEntities = loanerContactRepository.findLoanerContactEntityByLoanerItem_LoanerId(loanerId);
        return contactEntities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public LoanerContactDto saveLoanerContact(LoanerContactDto loanerContactDto) {
        LoanerContactEntity contactEntity = convertToEntity(loanerContactDto);
        LoanerContactEntity savedContact = loanerContactRepository.save(contactEntity);
        return convertToDto(savedContact);
    }

    @Override
    public LoanerContactDto updateLoanerContact(Integer contactId, LoanerContactDto loanerContactDto) {
        LoanerContactEntity existingContact = loanerContactRepository.findById(contactId)
                .orElseThrow(() -> new RuntimeException("LoanerContact not found with ID: " + contactId));

        existingContact.setPhoneNumber(loanerContactDto.getPhoneNumber());
        existingContact.setContactType(loanerContactDto.getContactType());
        existingContact.setChangeDate(loanerContactDto.getChangeDate());
        existingContact.setContactName(loanerContactDto.getContactName());
        existingContact.setIsCurrent(loanerContactDto.getIsCurrent());
        existingContact.setPriority(loanerContactDto.getPriority());

        LoanerContactEntity updatedContact = loanerContactRepository.save(existingContact);
        return convertToDto(updatedContact);
    }

    @Override
    public void deleteLoanerContact(Integer contactId) {
        loanerContactRepository.deleteById(contactId);
    }


    private LoanerContactDto convertToDto(LoanerContactEntity contactEntity) {
        LoanerContactDto contactDto = new LoanerContactDto();
        contactDto.setContactId(contactEntity.getContactId());
        contactDto.setPhoneNumber(contactEntity.getPhoneNumber());
        contactDto.setContactType(contactEntity.getContactType());
        contactDto.setChangeDate(contactEntity.getChangeDate());
        contactDto.setContactName(contactEntity.getContactName());
        contactDto.setIsCurrent(contactEntity.getIsCurrent());
        contactDto.setPriority(contactEntity.getPriority());
        contactDto.setLoanerId(contactEntity.getLoanerItem().getLoanerId());

        return contactDto;
    }

    private LoanerContactEntity convertToEntity(LoanerContactDto loanerContactDto) {
        LoanerContactEntity contactEntity = new LoanerContactEntity();
        contactEntity.setContactId(loanerContactDto.getContactId());
        contactEntity.setPhoneNumber(loanerContactDto.getPhoneNumber());
        contactEntity.setContactType(loanerContactDto.getContactType());
        contactEntity.setChangeDate(loanerContactDto.getChangeDate());
        contactEntity.setContactName(loanerContactDto.getContactName());
        contactEntity.setIsCurrent(loanerContactDto.getIsCurrent());
        contactEntity.setPriority(loanerContactDto.getPriority());

        LoanerEntity loanerEntity = loanerRepository.findById(loanerContactDto.getLoanerId())
                .orElseThrow(() -> new RuntimeException("Loaner not found with ID: " + loanerContactDto.getLoanerId()));

        contactEntity.setLoanerItem(loanerEntity);

        return contactEntity;
    }

}
