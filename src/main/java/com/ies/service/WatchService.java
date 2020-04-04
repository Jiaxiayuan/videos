package com.ies.service;

import com.ies.domain.User;
import com.ies.domain.Video;
import com.ies.domain.Watch;

public interface WatchService {
    Integer queryByVideoCount(Integer videoId);

    Watch getWatchByUserAndVideo(Integer id, Integer id1);

    void addWatch(User user, Video video);

    void updateWatch(Watch watch);
}
