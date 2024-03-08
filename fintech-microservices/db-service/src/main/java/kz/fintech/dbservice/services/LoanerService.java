package kz.fintech.dbservice.services;

import kz.fintech.dbservice.entities.LoanerEntity;
import kz.fintech.models.loaner.LoanerDto;

import java.util.List;

public interface LoanerService {
    LoanerDto getLoanerById(Integer loanerId);

    List<LoanerDto> getAllLoaners();

    LoanerDto saveLoaner(LoanerDto loaner);

    void deleteLoaner(Integer loanerId);

    LoanerDto convertToDto(LoanerEntity loanerEntity);

    LoanerEntity convertToEntity(LoanerDto loanerDto);

    List<LoanerDto> getAllLoanersByStatus();
}
