package kz.fintech.dbservice.services;

import kz.fintech.dbservice.entities.CommFqEntity;

import java.util.List;

public interface CommFqService {
    CommFqEntity createCommFq(CommFqEntity commFq);
    CommFqEntity getCommFqById(Integer id);
    List<CommFqEntity> getAllCommFq();
    CommFqEntity updateCommFq(Integer id, CommFqEntity commFq);
    void deleteCommFq(Integer id);
}
