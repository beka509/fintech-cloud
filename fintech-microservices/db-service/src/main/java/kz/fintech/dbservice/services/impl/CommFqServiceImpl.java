package kz.fintech.dbservice.services.impl;

import kz.fintech.dbservice.entities.CommFqEntity;
import kz.fintech.dbservice.repos.CommFqRepository;
import kz.fintech.dbservice.services.CommFqService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class CommFqServiceImpl implements CommFqService {
    private final CommFqRepository commFqRepository;

    @Override
    public CommFqEntity createCommFq(CommFqEntity commFq) {
        return commFqRepository.save(commFq);
    }

    @Override
    public CommFqEntity getCommFqById(Integer id) {
        return commFqRepository.findById(id).orElse(null);
    }

    @Override
    public List<CommFqEntity> getAllCommFq() {
        return commFqRepository.findAll();
    }

    @Override
    public CommFqEntity updateCommFq(Integer id, CommFqEntity commFq) {
        if (commFqRepository.existsById(id)) {
            commFq.setCommFqId(id);
            return commFqRepository.save(commFq);
        }
        return null;
    }

    @Override
    public void deleteCommFq(Integer id) {
        commFqRepository.deleteById(id);
    }
}
