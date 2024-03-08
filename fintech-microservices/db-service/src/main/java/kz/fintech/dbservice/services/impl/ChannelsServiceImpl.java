package kz.fintech.dbservice.services.impl;

import kz.fintech.dbservice.entities.ChannelsEntity;
import kz.fintech.dbservice.repos.ChannelsRepository;
import kz.fintech.dbservice.services.ChannelsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class ChannelsServiceImpl implements ChannelsService {
    private final ChannelsRepository channelsRepository;

    @Override
    public ChannelsEntity createChannels(ChannelsEntity channels) {
        return channelsRepository.save(channels);
    }

    @Override
    public ChannelsEntity getChannelsById(Integer id) {
        return channelsRepository.findById(id).orElse(null);
    }

    @Override
    public List<ChannelsEntity> getAllChannels() {
        return channelsRepository.findAll();
    }

    @Override
    public ChannelsEntity updateChannels(Integer id, ChannelsEntity channels) {
        if (channelsRepository.existsById(id)) {
            channels.setChannelsId(id);
            return channelsRepository.save(channels);
        }
        return null;
    }

    @Override
    public void deleteChannels(Integer id) {
        channelsRepository.deleteById(id);
    }
}
