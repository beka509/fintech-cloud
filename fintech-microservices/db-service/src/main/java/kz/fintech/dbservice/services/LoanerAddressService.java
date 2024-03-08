package kz.fintech.dbservice.services;


import kz.fintech.models.loaner.LoanerAddressDto;

import java.util.List;

public interface LoanerAddressService {
    LoanerAddressDto getLoanerAddressById(Integer addressId);

    List<LoanerAddressDto> getAllLoanerAddresses();

    LoanerAddressDto saveLoanerAddress(LoanerAddressDto loanerAddressDto);

    LoanerAddressDto updateLoanerAddress(Integer addressId, LoanerAddressDto loanerAddressDto);

    void deleteLoanerAddress(Integer addressId);

    List<LoanerAddressDto> getAddressesByLoanerId(Integer loanerId);

}
