package kz.fintech.dbservice.services.impl;

import kz.fintech.dbservice.entities.SegmentsEntity;
import kz.fintech.dbservice.repos.SegmentsRepository;
import kz.fintech.dbservice.services.SegmentsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class SegmentsServiceImpl implements SegmentsService {
    private final SegmentsRepository segmentsRepository;

    @Override
    public SegmentsEntity createSegments(SegmentsEntity segments) {
        return segmentsRepository.save(segments);
    }

    @Override
    public SegmentsEntity getSegmentsById(Integer id) {
        return segmentsRepository.findById(id).orElse(null);
    }

    @Override
    public List<SegmentsEntity> getAllSegments() {
        return segmentsRepository.findAll();
    }

    @Override
    public SegmentsEntity updateSegments(Integer id, SegmentsEntity segments) {
        if (segmentsRepository.existsById(id)) {
            segments.setSegmentId(id);
            return segmentsRepository.save(segments);
        }
        return null;
    }

    @Override
    public void deleteSegments(Integer id) {
        segmentsRepository.deleteById(id);
    }


}
