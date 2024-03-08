package kz.fintech.dbservice.services;

import kz.fintech.dbservice.entities.SequencesEntity;

import java.util.List;

public interface SequencesService {
    SequencesEntity createSequences(SequencesEntity sequences);
    SequencesEntity getSequencesById(Integer id);
    List<SequencesEntity> getAllSequences();
    SequencesEntity updateSequences(Integer id, SequencesEntity sequences);
    void deleteSequences(Integer id);
}
