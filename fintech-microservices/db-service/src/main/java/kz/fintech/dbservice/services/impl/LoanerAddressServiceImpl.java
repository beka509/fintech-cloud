package kz.fintech.dbservice.services.impl;

import kz.fintech.dbservice.entities.LoanerAddressEntity;
import kz.fintech.dbservice.entities.LoanerAddressTypeEntity;
import kz.fintech.dbservice.entities.LoanerEntity;
import kz.fintech.dbservice.repos.LoanerAddressRepository;
import kz.fintech.dbservice.repos.LoanerAddressTypeRepository;
import kz.fintech.dbservice.repos.LoanerRepository;
import kz.fintech.dbservice.services.LoanerAddressService;
import kz.fintech.models.loaner.LoanerAddressDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LoanerAddressServiceImpl implements LoanerAddressService {

    private final LoanerAddressRepository loanerAddressRepository;
    private final LoanerAddressTypeRepository loanerAddressTypeRepository;
    private final LoanerRepository loanerRepository;

    @Override
    public LoanerAddressDto getLoanerAddressById(Integer addressId) {
        LoanerAddressEntity addressEntity = loanerAddressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException("LoanerAddress not found with ID: " + addressId));
        return convertToDto(addressEntity);
    }

    @Override
    public List<LoanerAddressDto> getAllLoanerAddresses() {
        List<LoanerAddressEntity> addressEntities = loanerAddressRepository.findAll();
        return addressEntities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public LoanerAddressDto saveLoanerAddress(LoanerAddressDto loanerAddressDto) {
        LoanerAddressEntity addressEntity = convertToEntity(loanerAddressDto);
        LoanerAddressEntity savedAddress = loanerAddressRepository.save(addressEntity);
        return convertToDto(savedAddress);
    }

    @Override
    public LoanerAddressDto updateLoanerAddress(Integer addressId, LoanerAddressDto loanerAddressDto) {
        LoanerAddressEntity existingAddress = loanerAddressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException("LoanerAddress not found with ID: " + addressId));

        // Обновляем поля
        existingAddress.setChangeDate(loanerAddressDto.getChangeDate());
        existingAddress.setRegion(loanerAddressDto.getRegion());
        existingAddress.setCity(loanerAddressDto.getCity());
        existingAddress.setStreet(loanerAddressDto.getStreet());
        existingAddress.setZipCode(loanerAddressDto.getZipCode());
        existingAddress.setKato(loanerAddressDto.getKato());
        existingAddress.setManagerId(loanerAddressDto.getManagerId());

        LoanerAddressTypeEntity addressTypeEntity = loanerAddressTypeRepository.findById(loanerAddressDto.getAddressTypeId())
                .orElseThrow(() -> new RuntimeException("LoanerAddressType not found with ID: " + loanerAddressDto.getAddressTypeId()));
        existingAddress.setLoanerAddressTypeItem(addressTypeEntity);

        LoanerEntity loanerEntity = loanerRepository.findById(loanerAddressDto.getLoanerId())
                .orElseThrow(() -> new RuntimeException("Loaner not found with ID: " + loanerAddressDto.getLoanerId()));
        existingAddress.setLoanerItem(loanerEntity);

        LoanerAddressEntity updatedAddress = loanerAddressRepository.save(existingAddress);
        return convertToDto(updatedAddress);
    }

    @Override
    public void deleteLoanerAddress(Integer addressId) {
        loanerAddressRepository.deleteById(addressId);
    }

    @Override
    public List<LoanerAddressDto> getAddressesByLoanerId(Integer loanerId) {
        return this.getAllLoanerAddresses().stream().filter(x->x.getLoanerId()==loanerId).collect(Collectors.toList());
    }

    private LoanerAddressDto convertToDto(LoanerAddressEntity addressEntity) {
        LoanerAddressDto addressDto = new LoanerAddressDto();
        addressDto.setAddressId(addressEntity.getAddressId());
        addressDto.setAddressTypeId(addressEntity.getLoanerAddressTypeItem().getTypeId());
        addressDto.setChangeDate(addressEntity.getChangeDate());
        addressDto.setRegion(addressEntity.getRegion());
        addressDto.setCity(addressEntity.getCity());
        addressDto.setStreet(addressEntity.getStreet());
        addressDto.setZipCode(addressEntity.getZipCode());
        addressDto.setKato(addressEntity.getKato());
        addressDto.setManagerId(addressEntity.getManagerId());
        addressDto.setLoanerId(addressEntity.getLoanerItem().getLoanerId());
        return addressDto;
    }

    private LoanerAddressEntity convertToEntity(LoanerAddressDto addressDto) {
        LoanerAddressEntity addressEntity = new LoanerAddressEntity();
        addressEntity.setAddressId(addressDto.getAddressId());
        addressEntity.setChangeDate(addressDto.getChangeDate());
        addressEntity.setRegion(addressDto.getRegion());
        addressEntity.setCity(addressDto.getCity());
        addressEntity.setStreet(addressDto.getStreet());
        addressEntity.setZipCode(addressDto.getZipCode());
        addressEntity.setKato(addressDto.getKato());
        addressEntity.setManagerId(addressDto.getManagerId());

        // Получаем и устанавливаем связанные сущности
        LoanerAddressTypeEntity addressTypeEntity = loanerAddressTypeRepository.findById(addressDto.getAddressTypeId())
                .orElseThrow(() -> new RuntimeException("LoanerAddressType not found with ID: " + addressDto.getAddressTypeId()));
        addressEntity.setLoanerAddressTypeItem(addressTypeEntity);

        LoanerEntity loanerEntity = loanerRepository.findById(addressDto.getLoanerId())
                .orElseThrow(() -> new RuntimeException("Loaner not found with ID: " + addressDto.getLoanerId()));
        addressEntity.setLoanerItem(loanerEntity);

        return addressEntity;
    }
}
