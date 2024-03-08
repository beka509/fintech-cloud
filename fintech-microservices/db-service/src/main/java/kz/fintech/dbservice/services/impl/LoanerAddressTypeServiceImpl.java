package kz.fintech.dbservice.services.impl;

import kz.fintech.dbservice.entities.LoanerAddressTypeEntity;
import kz.fintech.dbservice.repos.LoanerAddressTypeRepository;
import kz.fintech.dbservice.services.LoanerAddressTypeService;
import kz.fintech.models.loaner.LoanerAddressTypeDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LoanerAddressTypeServiceImpl implements LoanerAddressTypeService {

    private final LoanerAddressTypeRepository loanerAddressTypeRepository;

    @Override
    public LoanerAddressTypeDto getLoanerAddressTypeById(Integer typeId) {
        LoanerAddressTypeEntity addressTypeEntity = loanerAddressTypeRepository.findById(typeId)
                .orElseThrow(() -> new RuntimeException("LoanerAddressType not found with ID: " + typeId));

        return convertToDto(addressTypeEntity);
    }

    @Override
    public List<LoanerAddressTypeDto> getAllLoanerAddressTypes() {
        List<LoanerAddressTypeEntity> addressTypeEntities = loanerAddressTypeRepository.findAll();
        return addressTypeEntities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public LoanerAddressTypeDto saveLoanerAddressType(LoanerAddressTypeDto loanerAddressTypeDto) {
        LoanerAddressTypeEntity addressTypeEntity = convertToEntity(loanerAddressTypeDto);
        LoanerAddressTypeEntity savedAddressType = loanerAddressTypeRepository.save(addressTypeEntity);
        return convertToDto(savedAddressType);
    }

    @Override
    public LoanerAddressTypeDto updateLoanerAddressType(Integer typeId, LoanerAddressTypeDto loanerAddressTypeDto) {
        LoanerAddressTypeEntity existingAddressType = loanerAddressTypeRepository.findById(typeId)
                .orElseThrow(() -> new RuntimeException("LoanerAddressType not found with ID: " + typeId));

        // Обновляем поля
        existingAddressType.setName(loanerAddressTypeDto.getName());

        LoanerAddressTypeEntity updatedAddressType = loanerAddressTypeRepository.save(existingAddressType);
        return convertToDto(updatedAddressType);
    }

    @Override
    public void deleteLoanerAddressType(Integer typeId) {
        loanerAddressTypeRepository.deleteById(typeId);
    }

    private LoanerAddressTypeDto convertToDto(LoanerAddressTypeEntity addressTypeEntity) {
        LoanerAddressTypeDto addressTypeDto = new LoanerAddressTypeDto();
        addressTypeDto.setTypeId(addressTypeEntity.getTypeId());
        addressTypeDto.setName(addressTypeEntity.getName());
        return addressTypeDto;
    }

    private LoanerAddressTypeEntity convertToEntity(LoanerAddressTypeDto addressTypeDto) {
        LoanerAddressTypeEntity addressTypeEntity = new LoanerAddressTypeEntity();
        addressTypeEntity.setTypeId(addressTypeDto.getTypeId());
        addressTypeEntity.setName(addressTypeDto.getName());
        return addressTypeEntity;
    }
}

