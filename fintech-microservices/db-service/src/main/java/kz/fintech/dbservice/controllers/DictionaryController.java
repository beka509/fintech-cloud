package kz.fintech.dbservice.controllers;

import kz.fintech.dbservice.entities.ChannelsEntity;
import kz.fintech.dbservice.entities.CommFqEntity;
import kz.fintech.dbservice.entities.ScriptsEntity;
import kz.fintech.dbservice.entities.SequencesEntity;
import kz.fintech.dbservice.services.*;
import kz.fintech.models.dictionary.Channels;
import kz.fintech.models.dictionary.CommFq;
import kz.fintech.models.dictionary.Scripts;
import kz.fintech.models.dictionary.Sequences;
import kz.fintech.models.loaner.LoanerAddressTypeDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/dictionary")
public class DictionaryController {

    private final ChannelsService channelsService;
    private final SequencesService sequencesService;
    private final CommFqService commFqService;
    private final ScriptsService scriptsService;
    private final LoanerAddressTypeService loanerAddressTypeService;

    @PostMapping("/channels/add")
    public Channels createChannels(@RequestBody Channels channels) {
        ChannelsEntity entity = new ChannelsEntity();
        entity.setName(channels.getName());
        ChannelsEntity createdEntity = channelsService.createChannels(entity);
        return convertToDtoChannels(createdEntity);
    }

    @GetMapping("/channels/view/{id}")
    public Channels getChannelsById(@PathVariable("id") Integer id) {
        ChannelsEntity entity = channelsService.getChannelsById(id);
        if (entity != null) {
            return convertToDtoChannels(entity);
        }
        return null;
    }

    @GetMapping("/channels/view/all")
    public List<Channels> getAllChannels() {
        List<ChannelsEntity> entities = channelsService.getAllChannels();
        return entities.stream()
                .map(this::convertToDtoChannels)
                .collect(Collectors.toList());
    }

    @PutMapping("/channels/update/{id}")
    public Channels updateChannels(@PathVariable("id") Integer id, @RequestBody Channels channels) {
        ChannelsEntity existingEntity = channelsService.getChannelsById(id);
        if (existingEntity != null) {
            existingEntity.setName(channels.getName());
            ChannelsEntity updatedEntity = channelsService.updateChannels(id, existingEntity);
            return convertToDtoChannels(updatedEntity);
        }
        return null;
    }

    @DeleteMapping("/channels/delete/{id}")
    public void deleteChannels(@PathVariable("id") Integer id) {
        channelsService.deleteChannels(id);
    }

    private Channels convertToDtoChannels(ChannelsEntity entity) {
        Channels dto = new Channels();
        dto.setChannelsId(entity.getChannelsId());
        dto.setName(entity.getName());
        return dto;
    }


    @PostMapping("/sequences/add")
    public Sequences createSequences(@RequestBody Sequences sequences) {
        SequencesEntity entity = new SequencesEntity();
        entity.setName(sequences.getName());
        SequencesEntity createdEntity = sequencesService.createSequences(entity);
        return convertToDtoSecuences(createdEntity);
    }

    @GetMapping("/sequences/view/{id}")
    public Sequences getSequencesById(@PathVariable("id") Integer id) {
        SequencesEntity entity = sequencesService.getSequencesById(id);
        if (entity != null) {
            return convertToDtoSecuences(entity);
        }
        return null;
    }

    @GetMapping("/sequences/view/all")
    public List<Sequences> getAllSequences() {
        List<SequencesEntity> entities = sequencesService.getAllSequences();
        return entities.stream()
                .map(this::convertToDtoSecuences)
                .collect(Collectors.toList());
    }

    @PutMapping("/sequences/update/{id}")
    public Sequences updateSequences(@PathVariable("id") Integer id, @RequestBody Sequences sequences) {
        SequencesEntity existingEntity = sequencesService.getSequencesById(id);
        if (existingEntity != null) {
            existingEntity.setName(sequences.getName());
            SequencesEntity updatedEntity = sequencesService.updateSequences(id, existingEntity);
            return convertToDtoSecuences(updatedEntity);
        }
        return null;
    }

    @DeleteMapping("/sequences/delete/{id}")
    public void deleteSequences(@PathVariable("id") Integer id) {
        sequencesService.deleteSequences(id);
    }

    private Sequences convertToDtoSecuences(SequencesEntity entity) {
        Sequences dto = new Sequences();
        dto.setSequencesId(entity.getSequencesId());
        dto.setName(entity.getName());
        return dto;
    }

    @PostMapping("/commfq/add")
    public CommFq createCommFq(@RequestBody CommFq commFq) {
        CommFqEntity entity = new CommFqEntity();
        entity.setName(commFq.getName());
        CommFqEntity createdEntity = commFqService.createCommFq(entity);
        return convertToDtoComFq(createdEntity);
    }

    @GetMapping("/commfq/view/{id}")
    public CommFq getCommFqById(@PathVariable("id") Integer id) {
        CommFqEntity entity = commFqService.getCommFqById(id);
        if (entity != null) {
            return convertToDtoComFq(entity);
        }
        return null;
    }

    @GetMapping("/commfq/view/all")
    public List<CommFq> getAllCommFq() {
        List<CommFqEntity> entities = commFqService.getAllCommFq();
        return entities.stream()
                .map(this::convertToDtoComFq)
                .collect(Collectors.toList());
    }

    @PutMapping("/commfq/update/{id}")
    public CommFq updateCommFq(@PathVariable("id") Integer id, @RequestBody CommFq commFq) {
        CommFqEntity existingEntity = commFqService.getCommFqById(id);
        if (existingEntity != null) {
            existingEntity.setName(commFq.getName());
            CommFqEntity updatedEntity = commFqService.updateCommFq(id, existingEntity);
            return convertToDtoComFq(updatedEntity);
        }
        return null;
    }

    @DeleteMapping("/commfq/delete/{id}")
    public void deleteCommFq(@PathVariable("id") Integer id) {
        commFqService.deleteCommFq(id);
    }

    private CommFq convertToDtoComFq(CommFqEntity entity) {
        CommFq dto = new CommFq();
        dto.setCommFqId(entity.getCommFqId());
        dto.setName(entity.getName());
        return dto;
    }

    @PostMapping("/scripts/add")
    public Scripts createScripts(@RequestBody Scripts scripts) {
        ScriptsEntity entity = new ScriptsEntity();
        entity.setName(scripts.getName());
        entity.setCode(scripts.getCode());
        ScriptsEntity createdEntity = scriptsService.createScripts(entity);
        return convertToDtoScripts(createdEntity);
    }

    @GetMapping("/scripts/view/{id}")
    public Scripts getScriptsById(@PathVariable("id") Integer id) {
        ScriptsEntity entity = scriptsService.getScriptsById(id);
        if (entity != null) {
            return convertToDtoScripts(entity);
        }
        return null;
    }

    @GetMapping("/scripts/view/all")
    public List<Scripts> getAllScripts() {
        List<ScriptsEntity> entities = scriptsService.getAllScripts();
        return entities.stream()
                .map(this::convertToDtoScripts)
                .collect(Collectors.toList());
    }

    @PutMapping("/scripts/update/{id}")
    public Scripts updateScripts(@PathVariable("id") Integer id, @RequestBody Scripts scripts) {
        ScriptsEntity existingEntity = scriptsService.getScriptsById(id);
        if (existingEntity != null) {
            existingEntity.setName(scripts.getName());
            existingEntity.setCode(scripts.getCode());
            ScriptsEntity updatedEntity = scriptsService.updateScripts(id, existingEntity);
            return convertToDtoScripts(updatedEntity);
        }
        return null;
    }

    @DeleteMapping("/scripts/delete/{id}")
    public void deleteScripts(@PathVariable("id") Integer id) {
        scriptsService.deleteScripts(id);
    }

    private Scripts convertToDtoScripts(ScriptsEntity entity) {
        Scripts dto = new Scripts();
        dto.setScriptsId(entity.getScriptsId());
        dto.setName(entity.getName());
        dto.setCode(entity.getCode());
        return dto;
    }

    @GetMapping("/loaner/address/type/view/{typeId}")
    public LoanerAddressTypeDto getLoanerAddressType(@PathVariable("typeId") Integer typeId) {
        return loanerAddressTypeService.getLoanerAddressTypeById(typeId);
    }

    @GetMapping("/loaner/address/type/view/all")
    public ResponseEntity<List<LoanerAddressTypeDto>> getAllLoanerAddressTypes() {
        List<LoanerAddressTypeDto> loanerAddressTypeDtos = loanerAddressTypeService.getAllLoanerAddressTypes();
        return ResponseEntity.ok(loanerAddressTypeDtos);
    }

    @PostMapping("/loaner/address/type/add")
    public LoanerAddressTypeDto createLoanerAddressType(@RequestBody LoanerAddressTypeDto loanerAddressTypeDto) {
        return loanerAddressTypeService.saveLoanerAddressType(loanerAddressTypeDto);
    }

    @PutMapping("/loaner/address/type/update/{typeId}")
    public ResponseEntity<LoanerAddressTypeDto> updateLoanerAddressType(
            @PathVariable("typeId") Integer typeId,
            @RequestBody LoanerAddressTypeDto loanerAddressTypeDto
    ) {
        LoanerAddressTypeDto updatedLoanerAddressTypeDto = loanerAddressTypeService.updateLoanerAddressType(typeId, loanerAddressTypeDto);
        return ResponseEntity.ok(updatedLoanerAddressTypeDto);
    }

    @DeleteMapping("/loaner/address/type/delete/{typeId}")
    public ResponseEntity<Void> deleteLoanerAddressType(@PathVariable("typeId") Integer typeId) {
        loanerAddressTypeService.deleteLoanerAddressType(typeId);
        return ResponseEntity.noContent().build();
    }

}
