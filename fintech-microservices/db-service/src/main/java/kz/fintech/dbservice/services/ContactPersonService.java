package kz.fintech.dbservice.services;

import kz.fintech.models.loaner.ContactPersonDto;

import java.util.List;

public interface ContactPersonService {
    ContactPersonDto getContactPersonById(Integer personId);

    List<ContactPersonDto> getAllContactPersons();

    ContactPersonDto createContactPerson(ContactPersonDto contactPersonDto);

    ContactPersonDto updateContactPerson(Integer personId, ContactPersonDto contactPersonDto);

    void deleteContactPerson(Integer personId);
}
