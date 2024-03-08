package kz.fintech.dbservice.controllers;

import kz.fintech.dbservice.entities.StrategyAlgorithmsEntity;
import kz.fintech.dbservice.entities.StrategyEntity;
import kz.fintech.dbservice.services.*;
import kz.fintech.models.strategy.StrategyAlgorithmsDto;
import kz.fintech.models.strategy.StrategyDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/strategy")
public class StrategyController {
    private final StrategyAlgorithmsService strategyAlgorithmsService;
    private final StrategyService strategyService;
    private final SegmentsService segmentsService;
    private final ChannelsService channelsService;
    private final CommFqService commFqService;
    private final ScriptsService scriptsService;
    private final SequencesService sequencesService;



    // Create a new strategy
    @PostMapping("/add")
    public StrategyDto createStrategy(@RequestBody StrategyDto strategyDto) {
        StrategyEntity strategyEntity = strategyService.createStrategyItem(convertToEntityStrategy(strategyDto));
        return convertToStrategyDto(strategyEntity);
    }

    // Get strategy by ID
    @GetMapping("/view/{id}")
    public StrategyDto getStrategyById(@PathVariable("id") Integer id) {
        StrategyEntity strategyEntity = strategyService.getStrategyItemById(id);
        return (strategyEntity != null) ? convertToStrategyDto(strategyEntity) : null;
    }

    // Get all strategies
    @GetMapping("/view/all")
    public List<StrategyDto> getAllStrategies() {
        List<StrategyEntity> strategyEntities = strategyService.getAllStrategyItems();
        return strategyEntities.stream()
                .map(this::convertToStrategyDto)
                .collect(Collectors.toList());
    }

    // Update strategy by ID
    @PutMapping("/update/{id}")
    public StrategyDto updateStrategy(@PathVariable("id") Integer id, @RequestBody StrategyDto strategyDto) {
        StrategyEntity strategyEntity = convertToEntityStrategy(strategyDto);
        strategyEntity.setId(id);
        return convertToStrategyDto(strategyService.updateStrategyItem(strategyDto.getId(), strategyEntity));
    }

    // Delete strategy by ID
    @DeleteMapping("/delete/{id}")
    public void deleteStrategy(@PathVariable("id") Integer id) {
        strategyService.deleteStrategyItem(id);
    }

    private StrategyDto convertToStrategyDto(StrategyEntity strategyEntity) {
        StrategyDto dto = new StrategyDto();
        dto.setId(strategyEntity.getId());
        dto.setName(strategyEntity.getName());
        dto.setDescription(strategyEntity.getDescription());
        dto.setSegmentId(strategyEntity.getSegmentItem().getSegmentId());
        dto.setSegmentName(strategyEntity.getSegmentItem().getName());
        dto.setTimeStart(strategyEntity.getTimeStart());
        return dto;
    }

    private StrategyEntity convertToEntityStrategy(StrategyDto strategyDto) {
        StrategyEntity entity = new StrategyEntity();
        entity.setId(strategyDto.getId());
        entity.setName(strategyDto.getName());
        entity.setDescription(strategyDto.getDescription());
        entity.setSegmentItem(segmentsService.getSegmentsById(strategyDto.getSegmentId()));
        entity.setTimeStart(strategyDto.getTimeStart());
        return entity;
    }


    @PostMapping("/strategy-algrm/add")
    public StrategyAlgorithmsDto createStrategyAlgorithm(@RequestBody StrategyAlgorithmsDto strategyAlgorithm) {
        StrategyAlgorithmsEntity entity = convertToEntityStrategyAlgrm(strategyAlgorithm);
        StrategyAlgorithmsEntity createdEntity = strategyAlgorithmsService.createStrategyAlgorithm(entity);
        return convertToStrategyDto(createdEntity);
    }

    @GetMapping("/strategy-algrm/view/{id}")
    public StrategyAlgorithmsDto getStrategyAlgorithmById(@PathVariable("id") Integer id) {
        StrategyAlgorithmsEntity entity = strategyAlgorithmsService.getStrategyAlgorithmById(id);
        if (entity != null) {
            return convertToStrategyDto(entity);
        }
        return null;
    }

    @GetMapping("/strategy-algrm/view/all")
    public List<StrategyAlgorithmsDto> getAllStrategyAlgorithms() {
        List<StrategyAlgorithmsEntity> entities = strategyAlgorithmsService.getAllStrategyAlgorithms();
        return entities.stream()
                .map(this::convertToStrategyDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/strategy-algrm/update/{id}")
    public StrategyAlgorithmsDto updateStrategyAlgorithm(@PathVariable("id") Integer id, @RequestBody StrategyAlgorithmsDto strategyAlgorithm) {
//        StrategyAlgorithmsEntity existingEntity = strategyAlgorithmsService.getStrategyAlgorithmById(id);
//        if (existingEntity != null) {
            StrategyAlgorithmsEntity updatedEntityConvert =convertToEntityStrategyAlgrm(strategyAlgorithm);
            StrategyAlgorithmsEntity updatedEntity = strategyAlgorithmsService.updateStrategyAlgorithm(id, updatedEntityConvert);
            return convertToStrategyDto(updatedEntity);
//        }
//        return null;
    }

    @DeleteMapping("/strategy-algrm/delete/{id}")
    public void deleteStrategyAlgorithm(@PathVariable("id") Integer id) {
        strategyAlgorithmsService.deleteStrategyAlgorithm(id);
    }

//    @GetMapping("/strategy-algrm/by_strategy/{strategyId}")
//    public List<StrategyAlgorithmsDto> getStrategyAlgorithmsByStrategyId(@PathVariable Integer strategyId) {
//        List<StrategyAlgorithmsEntity> entities = strategyAlgorithmsService.getStrategyAlgorithmsByStrategyId(strategyId);
//        return entities.stream()
//                .map(this::convertToStrategyDto)
//                .collect(Collectors.toList());
//    }

    @GetMapping("/strategy-algrm/view-by-strategy/{id}")
    public List<StrategyAlgorithmsDto> getAllStrategyAlgorithms(@PathVariable("id") Integer id) {
        List<StrategyAlgorithmsEntity> entities = strategyAlgorithmsService.getAllStrategyAlgorithms();
        List<StrategyAlgorithmsEntity> res = entities.stream().filter(x->x.getStrategyItem().getId() == id).collect(Collectors.toList());
        return res.stream()
                .map(this::convertToStrategyDto)
                .collect(Collectors.toList());
    }

    private StrategyAlgorithmsDto convertToStrategyDto(StrategyAlgorithmsEntity entity) {
        StrategyAlgorithmsDto dto = new StrategyAlgorithmsDto();
        dto.setStrategyAlgorithmsId(entity.getStrategyAlgorithmsId());
        dto.setStrategyAlgorithmsId(entity.getStrategyAlgorithmsId());
        dto.setStrategyId(entity.getStrategyItem().getId());
        dto.setChannelId(entity.getChannelItem().getChannelsId());
        dto.setCommFqId(entity.getCommFqItem().getCommFqId());
        dto.setSequenceId(entity.getSequenceItem().getSequencesId());
        dto.setScriptsId(entity.getScriptsItem().getScriptsId());

        dto.setStrategyName(entity.getStrategyItem().getName());
        dto.setChannelName(entity.getChannelItem().getName());
        dto.setCommFqName(entity.getCommFqItem().getName());
        dto.setSequenceName(entity.getSequenceItem().getName());
        dto.setScriptsName(entity.getScriptsItem().getName());

        return dto;
    }


    private StrategyAlgorithmsEntity convertToEntityStrategyAlgrm(StrategyAlgorithmsDto strategyAlgorithmsDto) {
        StrategyAlgorithmsEntity entity = new StrategyAlgorithmsEntity();
//        entity.setStrategyAlgorithmsId(strategyAlgorithmsDto.getStrategyAlgorithmsId() != null ? strategyAlgorithmsDto.getStrategyAlgorithmsId() : 999);
        entity.setStrategyItem(strategyService.getStrategyItemById(strategyAlgorithmsDto.getStrategyId()));
        entity.setChannelItem(channelsService.getChannelsById(strategyAlgorithmsDto.getChannelId()));
        entity.setSequenceItem(sequencesService.getSequencesById(strategyAlgorithmsDto.getSequenceId()));
        entity.setScriptsItem(scriptsService.getScriptsById(strategyAlgorithmsDto.getScriptsId()));
        entity.setCommFqItem(commFqService.getCommFqById(strategyAlgorithmsDto.getCommFqId()));
        return entity;
    }

}
