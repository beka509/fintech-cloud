package kz.fintech.dbservice.services;

import kz.fintech.models.loaner.LoanerAddressTypeDto;

import java.util.List;

public interface LoanerAddressTypeService {
    LoanerAddressTypeDto getLoanerAddressTypeById(Integer typeId);

    List<LoanerAddressTypeDto> getAllLoanerAddressTypes();

    LoanerAddressTypeDto saveLoanerAddressType(LoanerAddressTypeDto loanerAddressTypeDto);

    LoanerAddressTypeDto updateLoanerAddressType(Integer typeId, LoanerAddressTypeDto loanerAddressTypeDto);

    void deleteLoanerAddressType(Integer typeId);
}

