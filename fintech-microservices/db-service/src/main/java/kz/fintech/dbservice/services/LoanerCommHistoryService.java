package kz.fintech.dbservice.services;

import kz.fintech.models.loaner.LoanerCommHistoryDto;

import java.util.List;

public interface LoanerCommHistoryService {

    LoanerCommHistoryDto addLoanerCommHistory(LoanerCommHistoryDto loanerCommHistoryDTO);

    List<LoanerCommHistoryDto> getAllLoanerCommHistory();

    List<LoanerCommHistoryDto> getLoanerCommHistoryByClientId(Integer clientId);
}
