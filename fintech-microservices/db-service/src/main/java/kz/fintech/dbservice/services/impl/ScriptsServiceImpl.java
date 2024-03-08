package kz.fintech.dbservice.services.impl;

import kz.fintech.dbservice.entities.ScriptsEntity;
import kz.fintech.dbservice.repos.ScriptsRepository;
import kz.fintech.dbservice.services.ScriptsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class ScriptsServiceImpl implements ScriptsService {
    private final ScriptsRepository scriptsRepository;

    @Override
    public ScriptsEntity createScripts(ScriptsEntity scripts) {
        return scriptsRepository.save(scripts);
    }

    @Override
    public ScriptsEntity getScriptsById(Integer id) {
        return scriptsRepository.findById(id).orElse(null);
    }

    @Override
    public List<ScriptsEntity> getAllScripts() {
        return scriptsRepository.findAll();
    }

    @Override
    public ScriptsEntity updateScripts(Integer id, ScriptsEntity scripts) {
        if (scriptsRepository.existsById(id)) {
            scripts.setScriptsId(id);
            return scriptsRepository.save(scripts);
        }
        return null;
    }

    @Override
    public void deleteScripts(Integer id) {
        scriptsRepository.deleteById(id);
    }
}
