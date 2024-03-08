package kz.fintech.dbservice.services;

import kz.fintech.dbservice.entities.ChannelsEntity;

import java.util.List;

public interface ChannelsService {
    ChannelsEntity createChannels(ChannelsEntity channels);
    ChannelsEntity getChannelsById(Integer id);
    List<ChannelsEntity> getAllChannels();
    ChannelsEntity updateChannels(Integer id, ChannelsEntity channels);
    void deleteChannels(Integer id);
}
