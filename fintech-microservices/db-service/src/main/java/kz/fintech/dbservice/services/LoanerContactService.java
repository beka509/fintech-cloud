package kz.fintech.dbservice.services;

import kz.fintech.models.loaner.LoanerContactDto;

import java.util.List;

public interface LoanerContactService {
    LoanerContactDto getLoanerContactById(Integer contactId);

    List<LoanerContactDto> getAllLoanerContacts();

    List<LoanerContactDto> getLoanerContactByLoanerId(Integer loanerId);

    LoanerContactDto saveLoanerContact(LoanerContactDto loanerContactDto);

    LoanerContactDto updateLoanerContact(Integer contactId, LoanerContactDto loanerContactDto);

    void deleteLoanerContact(Integer contactId);
}


