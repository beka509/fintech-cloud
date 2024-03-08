package kz.fintech.dbservice.controllers;

import kz.fintech.dbservice.entities.SegmentsEntity;
import kz.fintech.models.SegmentCreationRequest;
import kz.fintech.dbservice.services.SegmentByClientsService;
import kz.fintech.dbservice.services.SegmentsService;
import kz.fintech.models.strategy.SegmentByClientsDTO;
import kz.fintech.models.strategy.SegmentDto;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/segment")
public class SegmentController {
    private final SegmentsService segmentsService;
    private final SegmentByClientsService segmentByClientsService;

    // Создание нового сегмента
    @PostMapping("/add")
    public SegmentDto createSegment(@RequestBody SegmentDto segmentsDTO) {
        SegmentsEntity segmentsEntity = segmentsService.createSegments(convertToEntitySegment(segmentsDTO));
        return convertToSegmentDto(segmentsEntity);
    }

    // Получение сегмента по ID
    @GetMapping("/view/{id}")
    public SegmentDto getSegmentById(@PathVariable("id") Integer id) {
        SegmentsEntity segmentsEntity = segmentsService.getSegmentsById(id);
        return (segmentsEntity != null) ? convertToSegmentDto(segmentsEntity) : null;
    }

    // Получение всех сегментов
    @GetMapping("/view/all")
    public List<SegmentDto> getAllSegments() {
        List<SegmentsEntity> strategyEntities = segmentsService.getAllSegments();
        return strategyEntities.stream()
                .map(this::convertToSegmentDto)
                .collect(Collectors.toList());
    }

    // Обновление сегмента по ID
    @PutMapping("/update/{id}")
    public SegmentDto updateSegment(@PathVariable("id") Integer id, @RequestBody SegmentDto segmentDto) {
        SegmentsEntity segmentsEntity = convertToEntitySegment(segmentDto);
        segmentsEntity.setSegmentId(id);
        return convertToSegmentDto(segmentsService.updateSegments(segmentDto.getSegmentId(), segmentsEntity));
    }

    // Удаление сегмента по ID
    @DeleteMapping("/delete/{id}")
    @SneakyThrows
    public void deleteSegment(@PathVariable("id") Integer id) {
        segmentByClientsService.deleteSegmentByClientsBySegmentId(id);
        segmentsService.deleteSegments(id);
    }

    private SegmentDto convertToSegmentDto(SegmentsEntity entity) {
        return SegmentDto.builder()
                .segmentId(entity.getSegmentId())
                .name(entity.getName())
                .descriptions(entity.getDescriptions())
                .build();
    }

    private SegmentsEntity convertToEntitySegment(SegmentDto dto) {
        SegmentsEntity entity = new SegmentsEntity();
        entity.setSegmentId(dto.getSegmentId());
        entity.setName(dto.getName());
        entity.setDescriptions(dto.getDescriptions());
        return entity;
    }

    @PostMapping("/segment-by-clients/add")
    public SegmentByClientsDTO createSegmentByClients(@RequestBody SegmentByClientsDTO segmentByClientsDTO) {
        SegmentByClientsDTO createdSegmentByClients = segmentByClientsService.createSegmentByClients(segmentByClientsDTO);
        return createdSegmentByClients;
    }

    @GetMapping("/segment-by-clients/view/{id}")
    public SegmentByClientsDTO getSegmentByClientsById(@PathVariable("id") Integer id) {
        SegmentByClientsDTO segmentByClients = segmentByClientsService.getSegmentByClientsById(id);
        if (segmentByClients != null) {
            return segmentByClients;
        } else {
            return null;
        }
    }

    @GetMapping("/segment-by-clients/view/all")
    public List<SegmentByClientsDTO> getAllSegmentByClients() {
        List<SegmentByClientsDTO> segmentByClientsList = segmentByClientsService.getAllSegmentByClients();
        return segmentByClientsList;
    }

    @PutMapping("/segment-by-clients/update/{id}")
    public SegmentByClientsDTO updateSegmentByClients(@PathVariable("id") Integer id,
                                                      @RequestBody SegmentByClientsDTO updatedSegmentByClientsDTO) {
        return segmentByClientsService.updateSegmentByClients(id, updatedSegmentByClientsDTO);

    }

    @DeleteMapping("/segment-by-clients/delete/{id}")
    public void deleteSegmentByClients(@PathVariable("id") Integer id) {
        segmentByClientsService.deleteSegmentByClients(id);
    }

    @PostMapping("/segment-by-clients/add-clients/{segmentId}")
    public void addClientsToSegment(@PathVariable("segmentId") Integer segmentId,
                                    @RequestBody List<Integer> clientIds) {
        segmentByClientsService.addClientsToSegment(segmentId, clientIds);
    }

    @DeleteMapping("/segment-by-clients/delete/by-segment/{segmentId}")
    public void deleteSegmentByClientsBySegmentId(@PathVariable("segmentId") Integer segmentId) {
        segmentByClientsService.deleteSegmentByClientsBySegmentId(segmentId);
    }

    @PostMapping("/create-and-add-clients")
    public void createSegmentAndAddClients(@RequestBody SegmentCreationRequest request) {
        segmentByClientsService.createSegmentAndAddClients(request.getSegmentDto(), request.getClientIds() );
    }
}


