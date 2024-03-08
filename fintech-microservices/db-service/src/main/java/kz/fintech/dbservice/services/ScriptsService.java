package kz.fintech.dbservice.services;

import kz.fintech.dbservice.entities.ScriptsEntity;

import java.util.List;

public interface ScriptsService {
    ScriptsEntity createScripts(ScriptsEntity scripts);
    ScriptsEntity getScriptsById(Integer id);
    List<ScriptsEntity> getAllScripts();
    ScriptsEntity updateScripts(Integer id, ScriptsEntity scripts);
    void deleteScripts(Integer id);
}
