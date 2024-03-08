package kz.fintech.dbservice.services.impl;

import kz.fintech.dbservice.entities.ClientEntity;
import kz.fintech.dbservice.entities.SegmentByClientsEntity;
import kz.fintech.dbservice.entities.SegmentsEntity;
import kz.fintech.dbservice.repos.ClientRepository;
import kz.fintech.dbservice.repos.SegmentByClientsRepository;
import kz.fintech.dbservice.repos.SegmentsRepository;
import kz.fintech.dbservice.services.SegmentByClientsService;
import kz.fintech.models.strategy.SegmentByClientsDTO;
import kz.fintech.models.strategy.SegmentDto;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SegmentByClientsServiceImpl implements SegmentByClientsService {
    private SegmentByClientsRepository segmentByClientsRepository;
    private SegmentsRepository segmentsRepository;
    private ClientRepository clientRepository;

    @Override
    public SegmentByClientsDTO createSegmentByClients(SegmentByClientsDTO segmentByClientsDTO) {
        SegmentByClientsEntity entity = convertToEntity(segmentByClientsDTO);
        SegmentByClientsEntity createdEntity = segmentByClientsRepository.save(entity);
        return convertToDTO(createdEntity);
    }

    @Override
    public SegmentByClientsDTO getSegmentByClientsById(Integer id) {
        SegmentByClientsEntity entity = segmentByClientsRepository.findById(id).orElse(null);
        return convertToDTO(entity);
    }

    @Override
    public List<SegmentByClientsDTO> getAllSegmentByClients() {
        List<SegmentByClientsEntity> entities = segmentByClientsRepository.findAll();
        return entities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SegmentByClientsDTO updateSegmentByClients(Integer id, SegmentByClientsDTO updatedSegmentByClientsDTO) {
        SegmentByClientsEntity existingEntity = segmentByClientsRepository.findById(id).orElse(null);
        if (existingEntity != null) {
            SegmentByClientsEntity updatedEntity = convertToEntity(updatedSegmentByClientsDTO);
            existingEntity.setSegment(updatedEntity.getSegment());
            existingEntity.setClient(updatedEntity.getClient());
            return convertToDTO(segmentByClientsRepository.save(existingEntity));
        }
        return null;
    }

    @Override
    public void deleteSegmentByClients(Integer id) {
        segmentByClientsRepository.deleteById(id);
    }

    @Override
    public void addClientsToSegment(Integer segmentId, List<Integer> clientIds) {
        SegmentsEntity segment = segmentsRepository.findById(segmentId).orElse(null);

        if (segment != null) {
            for (Integer clientId : clientIds) {
                ClientEntity client = clientRepository.findClientEntitiesByClientId(clientId);

                if (client != null) {
                    SegmentByClientsEntity segmentByClients = new SegmentByClientsEntity();
                    segmentByClients.setSegment(segment);
                    segmentByClients.setClient(client);
                    segmentByClientsRepository.save(segmentByClients);
                }
            }
        }
    }

    @Override
    @SneakyThrows
    public void deleteSegmentByClientsBySegmentId(Integer segmentId) {
        SegmentsEntity segment = segmentsRepository.findById(segmentId).orElse(null);
        segmentByClientsRepository.deleteBySegmentId(segment.getSegmentId());
    }

    @Override
    @Transactional
    public void createSegmentAndAddClients(SegmentDto segmentDto, List<Integer> clientIds) {
        try {
            SegmentsEntity segment = new SegmentsEntity();
            segment.setName(segmentDto.getName());
            segment.setDescriptions(segmentDto.getDescriptions());
            segmentsRepository.save(segment);

            for (Integer clientId : clientIds) {
                ClientEntity client = clientRepository.findClientEntitiesByClientId(clientId);
                if (client != null) {
                    SegmentByClientsEntity segmentByClients = new SegmentByClientsEntity();
                    segmentByClients.setSegment(segment);
                    segmentByClients.setClient(client);
                    segmentByClientsRepository.save(segmentByClients);
                } else {
               //     throw new RuntimeException("Client not found");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error occurred during segment creation and client addition");
        }
    }


    // Методы для конвертации
    private SegmentByClientsDTO convertToDTO(SegmentByClientsEntity entity) {
        SegmentByClientsDTO dto = new SegmentByClientsDTO();
        dto.setId(entity.getId());
        dto.setSegmentId(entity.getSegment().getSegmentId());
        dto.setClientId(entity.getClient().getClientId());
        return dto;
    }

    private SegmentByClientsEntity convertToEntity(SegmentByClientsDTO dto) {
        SegmentByClientsEntity entity = new SegmentByClientsEntity();
        entity.setId(dto.getId());
        return entity;
    }
}
