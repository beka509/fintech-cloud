package kz.fintech.dbservice.services.impl;

import kz.fintech.dbservice.entities.LoanerCallResultDemoEntity;
import kz.fintech.dbservice.repos.LoanerCallResultDemoRepository;
import kz.fintech.dbservice.services.LoanerCallResultDemoService;
import kz.fintech.models.loaner.LoanerCallResultDemoDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LoanerCallResultDemoServiceImpl implements LoanerCallResultDemoService {

    private final LoanerCallResultDemoRepository repository;

    @Override
    public LoanerCallResultDemoDto getById(Integer id) {
        Optional<LoanerCallResultDemoEntity> optionalEntity = repository.findById(id);
        return optionalEntity.map(this::convertToDto).orElse(null);
    }

    @Override
    public List<LoanerCallResultDemoDto> getAll() {
        List<LoanerCallResultDemoEntity> entities = repository.findAll();
        return entities.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public LoanerCallResultDemoDto save(LoanerCallResultDemoDto dto) {
        LoanerCallResultDemoEntity entity = convertToEntity(dto);
        LoanerCallResultDemoEntity savedEntity = repository.save(entity);
        return convertToDto(savedEntity);
    }

    @Override
    public LoanerCallResultDemoDto update(Integer id, LoanerCallResultDemoDto dto) {
        Optional<LoanerCallResultDemoEntity> optionalEntity = repository.findById(id);
        if (optionalEntity.isPresent()) {
            LoanerCallResultDemoEntity existingEntity = optionalEntity.get();
            updateEntityFromDto(dto, existingEntity);
            LoanerCallResultDemoEntity updatedEntity = repository.save(existingEntity);
            return convertToDto(updatedEntity);
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    private LoanerCallResultDemoDto convertToDto(LoanerCallResultDemoEntity entity) {
        LoanerCallResultDemoDto dto = new LoanerCallResultDemoDto();
        dto.setCallResultId(entity.getCallResultId());
        dto.setContactPersonName(entity.getContactPersonName());
        dto.setOverdueReason(entity.getOverdueReason());
        dto.setResultDesc(entity.getResultDesc());
        dto.setPromiseDate(entity.getPromiseDate());
        dto.setComment(entity.getComment());
        dto.setClientId(entity.getClientId());
        return dto;
    }

    private LoanerCallResultDemoEntity convertToEntity(LoanerCallResultDemoDto dto) {
        LoanerCallResultDemoEntity entity = new LoanerCallResultDemoEntity();
        entity.setCallResultId(dto.getCallResultId());
        entity.setContactPersonName(dto.getContactPersonName());
        entity.setOverdueReason(dto.getOverdueReason());
        entity.setResultDesc(dto.getResultDesc());
        entity.setPromiseDate(dto.getPromiseDate());
        entity.setComment(dto.getComment());
        entity.setClientId(dto.getClientId());
        return entity;
    }

    private void updateEntityFromDto(LoanerCallResultDemoDto dto, LoanerCallResultDemoEntity entity) {
        entity.setContactPersonName(dto.getContactPersonName());
        entity.setOverdueReason(dto.getOverdueReason());
        entity.setResultDesc(dto.getResultDesc());
        entity.setPromiseDate(dto.getPromiseDate());
        entity.setComment(dto.getComment());
        entity.setClientId(dto.getClientId());
    }

    @Override
    public List<LoanerCallResultDemoDto> getByClientId(Integer clientId) {
        List<LoanerCallResultDemoEntity> entities = repository.findByClientId(clientId);
        return entities.stream().map(this::convertToDto).collect(Collectors.toList());
    }
}
