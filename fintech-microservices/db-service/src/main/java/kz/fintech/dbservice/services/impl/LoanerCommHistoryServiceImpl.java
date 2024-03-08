package kz.fintech.dbservice.services.impl;

import kz.fintech.dbservice.entities.LoanerCommHistoryEntity;
import kz.fintech.dbservice.repos.LoanerCommHistoryRepository;
import kz.fintech.dbservice.services.LoanerCommHistoryService;
import kz.fintech.models.loaner.LoanerCommHistoryDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LoanerCommHistoryServiceImpl implements LoanerCommHistoryService {

    private final LoanerCommHistoryRepository loanerCommHistoryRepository;

    @Override
    public LoanerCommHistoryDto addLoanerCommHistory(LoanerCommHistoryDto loanerCommHistoryDto) {
        LoanerCommHistoryEntity loanerCommHistory = convertDtoToEntity(loanerCommHistoryDto);
        LoanerCommHistoryEntity savedLoanerCommHistory = loanerCommHistoryRepository.save(loanerCommHistory);
        return convertEntityToDto(savedLoanerCommHistory);
    }

    @Override
    public List<LoanerCommHistoryDto> getAllLoanerCommHistory() {
        List<LoanerCommHistoryEntity> loanerCommHistories = loanerCommHistoryRepository.findAll();
        return loanerCommHistories.stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<LoanerCommHistoryDto> getLoanerCommHistoryByClientId(Integer clientId) {
        List<LoanerCommHistoryEntity> loanerCommHistories = loanerCommHistoryRepository.findByClientId(clientId);
        return loanerCommHistories.stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    private LoanerCommHistoryEntity convertDtoToEntity(LoanerCommHistoryDto dto) {
        LoanerCommHistoryEntity entity = new LoanerCommHistoryEntity();
        entity.setCommHistoryId(dto.getCommHistoryId());
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
        entity.setCommunicationDirection(dto.getCommunicationDirection());
        entity.setContactNumber(dto.getContactNumber());
        entity.setResult(dto.getResult());
        entity.setManagerId(dto.getManagerId());
        entity.setClientId(dto.getClientId());
        return entity;
    }

    private LoanerCommHistoryDto convertEntityToDto(LoanerCommHistoryEntity entity) {
        LoanerCommHistoryDto dto = new LoanerCommHistoryDto();
        dto.setCommHistoryId(entity.getCommHistoryId());
        dto.setStartDate(entity.getStartDate());
        dto.setEndDate(entity.getEndDate());
        dto.setCommunicationDirection(entity.getCommunicationDirection());
        dto.setContactNumber(entity.getContactNumber());
        dto.setResult(entity.getResult());
        dto.setManagerId(entity.getManagerId());
        dto.setClientId(entity.getClientId());
        return dto;
    }
}
