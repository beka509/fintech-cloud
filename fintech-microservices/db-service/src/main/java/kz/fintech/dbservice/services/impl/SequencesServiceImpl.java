package kz.fintech.dbservice.services.impl;

import kz.fintech.dbservice.entities.SequencesEntity;
import kz.fintech.dbservice.repos.SequencesRepository;
import kz.fintech.dbservice.services.SequencesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class SequencesServiceImpl implements SequencesService {
    private final SequencesRepository sequencesRepository;

    @Override
    public SequencesEntity createSequences(SequencesEntity sequences) {
        return sequencesRepository.save(sequences);
    }

    @Override
    public SequencesEntity getSequencesById(Integer id) {
        return sequencesRepository.findById(id).orElse(null);
    }

    @Override
    public List<SequencesEntity> getAllSequences() {
        return sequencesRepository.findAll();
    }

    @Override
    public SequencesEntity updateSequences(Integer id, SequencesEntity sequences) {
        if (sequencesRepository.existsById(id)) {
            sequences.setSequencesId(id);
            return sequencesRepository.save(sequences);
        }
        return null;
    }

    @Override
    public void deleteSequences(Integer id) {
        sequencesRepository.deleteById(id);
    }
}
