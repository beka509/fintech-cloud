package kz.fintech.dbservice.services;

import kz.fintech.models.loaner.LoanerCallResultDemoDto;

import java.util.List;

public interface LoanerCallResultDemoService {
    LoanerCallResultDemoDto getById(Integer id);

    List<LoanerCallResultDemoDto> getAll();

    LoanerCallResultDemoDto save(LoanerCallResultDemoDto dto);

    LoanerCallResultDemoDto update(Integer id, LoanerCallResultDemoDto dto);

    void delete(Integer id);

    List<LoanerCallResultDemoDto> getByClientId(Integer loanerId);

}
