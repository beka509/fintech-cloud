package kz.fintech.dbservice.services.impl;

import kz.fintech.dbservice.entities.LoanerEntity;
import kz.fintech.dbservice.repos.LoanerRepository;
import kz.fintech.dbservice.services.LoanerService;
import kz.fintech.models.loaner.LoanerDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LoanerServiceImpl implements LoanerService {
    private final LoanerRepository loanerRepository;

    @Override
    public LoanerDto getLoanerById(Integer loanerId) {
        LoanerEntity loanerEntity = loanerRepository.findById(loanerId)
                .orElseThrow(() -> new RuntimeException("Loaner not found with ID: " + loanerId));

        return convertToDto(loanerEntity);
    }

    @Override
    public List<LoanerDto> getAllLoaners() {
        List<LoanerEntity> loanerEntities = loanerRepository.findAll();
        return loanerEntities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public LoanerDto saveLoaner(LoanerDto loanerDTO) {
        LoanerEntity loanerEntity = convertToEntity(loanerDTO);
        LoanerEntity savedLoaner = loanerRepository.save(loanerEntity);
        return convertToDto(savedLoaner);
    }

    @Override
    public void deleteLoaner(Integer loanerId) {
        loanerRepository.deleteById(loanerId);
    }

    @Override
    public LoanerDto convertToDto(LoanerEntity loanerEntity) {
        LoanerDto loanerDto = new LoanerDto();
        loanerDto.setLoanerId(loanerEntity.getLoanerId());
        loanerDto.setLastName(loanerEntity.getLastName());
        loanerDto.setFirstName(loanerEntity.getFirstName());
        loanerDto.setMiddleName(loanerEntity.getMiddleName());
        loanerDto.setIin(loanerEntity.getIin());
        loanerDto.setContractNumber(loanerEntity.getContractNumber());
        loanerDto.setLoanType(loanerEntity.getLoanType());
        loanerDto.setStartDate(loanerEntity.getStartDate());
        loanerDto.setEndDate(loanerEntity.getEndDate());
        loanerDto.setOverdue(loanerEntity.getOverdue());
        loanerDto.setLoanAmount(loanerEntity.getLoanAmount());
        loanerDto.setCurrency(loanerEntity.getCurrency());
        loanerDto.setIssuingBranch(loanerEntity.getIssuingBranch());
        loanerDto.setIsVip(loanerEntity.getIsVip());
        loanerDto.setIsBfl(loanerEntity.getIsBfl());
        loanerDto.setGuarantorLastName(loanerEntity.getGuarantorLastName());
        loanerDto.setGuarantorFirstName(loanerEntity.getGuarantorFirstName());
        loanerDto.setGuarantorMiddleName(loanerEntity.getGuarantorMiddleName());
        loanerDto.setGuarantorIin(loanerEntity.getGuarantorIin());
        loanerDto.setGuarantorIsVip(loanerEntity.getGuarantorIsVip());
        loanerDto.setGuarantorIsBfl(loanerEntity.getGuarantorIsBfl());
        loanerDto.setOverdueDays(loanerEntity.getOverdue_days());
        loanerDto.setStatus(loanerEntity.getStatus());
        return loanerDto;
    }


    @Override
    public LoanerEntity convertToEntity(LoanerDto loanerDto) {
        LoanerEntity loanerEntity = new LoanerEntity();
        loanerEntity.setLoanerId(loanerDto.getLoanerId());
        loanerEntity.setLastName(loanerDto.getLastName());
        loanerEntity.setFirstName(loanerDto.getFirstName());
        loanerEntity.setMiddleName(loanerDto.getMiddleName());
        loanerEntity.setIin(loanerDto.getIin());
        loanerEntity.setContractNumber(loanerDto.getContractNumber());
        loanerEntity.setLoanType(loanerDto.getLoanType());
        loanerEntity.setStartDate(loanerDto.getStartDate());
        loanerEntity.setEndDate(loanerDto.getEndDate());
        loanerEntity.setOverdue(loanerDto.getOverdue());
        loanerEntity.setLoanAmount(loanerDto.getLoanAmount());
        loanerEntity.setCurrency(loanerDto.getCurrency());
        loanerEntity.setIssuingBranch(loanerDto.getIssuingBranch());
        loanerEntity.setIsVip(loanerDto.getIsVip());
        loanerEntity.setIsBfl(loanerDto.getIsBfl());
        loanerEntity.setGuarantorLastName(loanerDto.getGuarantorLastName());
        loanerEntity.setGuarantorFirstName(loanerDto.getGuarantorFirstName());
        loanerEntity.setGuarantorMiddleName(loanerDto.getGuarantorMiddleName());
        loanerEntity.setGuarantorIin(loanerDto.getGuarantorIin());
        loanerEntity.setGuarantorIsVip(loanerDto.getGuarantorIsVip());
        loanerEntity.setGuarantorIsBfl(loanerDto.getGuarantorIsBfl());
        loanerEntity.setStatus(loanerDto.getStatus());
        loanerEntity.setOverdue_days(loanerDto.getOverdueDays());
        return loanerEntity;
    }

    @Override
    public List<LoanerDto> getAllLoanersByStatus() {
        return getAllLoaners().stream().filter(x->x.getStatus() == null).collect(Collectors.toList());
    }

}
