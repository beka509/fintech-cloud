package kz.fintech.dbservice.services.impl;

import kz.fintech.dbservice.entities.ContactPersonEntity;
import kz.fintech.dbservice.repos.ContactPersonRepository;
import kz.fintech.dbservice.services.ContactPersonService;
import kz.fintech.models.loaner.ContactPersonDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ContactPersonServiceImpl implements ContactPersonService {

    private final ContactPersonRepository contactPersonRepository;

    @Override
    public ContactPersonDto getContactPersonById(Integer personId) {
        ContactPersonEntity personEntity = contactPersonRepository.findById(personId)
                .orElseThrow(() -> new RuntimeException("ContactPerson not found with ID: " + personId));

        return convertToDto(personEntity);
    }

    @Override
    public List<ContactPersonDto> getAllContactPersons() {
        List<ContactPersonEntity> personEntities = contactPersonRepository.findAll();
        return personEntities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ContactPersonDto createContactPerson(ContactPersonDto contactPersonDto) {
        ContactPersonEntity personEntity = convertToEntity(contactPersonDto);
        ContactPersonEntity savedPerson = contactPersonRepository.save(personEntity);
        return convertToDto(savedPerson);
    }

    @Override
    public ContactPersonDto updateContactPerson(Integer personId, ContactPersonDto contactPersonDto) {
        ContactPersonEntity existingPerson = contactPersonRepository.findById(personId)
                .orElseThrow(() -> new RuntimeException("ContactPerson not found with ID: " + personId));

        // Обновляем поля
        existingPerson.setName(contactPersonDto.getName());

        ContactPersonEntity updatedPerson = contactPersonRepository.save(existingPerson);
        return convertToDto(updatedPerson);
    }

    @Override
    public void deleteContactPerson(Integer personId) {
        contactPersonRepository.deleteById(personId);
    }

    private ContactPersonDto convertToDto(ContactPersonEntity personEntity) {
        ContactPersonDto personDto = new ContactPersonDto();
        personDto.setPersonId(personEntity.getPersonId());
        personDto.setName(personEntity.getName());
        return personDto;
    }

    private ContactPersonEntity convertToEntity(ContactPersonDto personDto) {
        ContactPersonEntity personEntity = new ContactPersonEntity();
        personEntity.setPersonId(personDto.getPersonId());
        personEntity.setName(personDto.getName());
        return personEntity;
    }
}
