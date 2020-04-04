package com.ies.service.impl;

import com.ies.domain.User;
import com.ies.domain.Video;
import com.ies.domain.Watch;
import com.ies.domain.WatchCriteria;
import com.ies.mapper.WatchMapper;
import com.ies.service.WatchService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @program: videos
 * @description:
 * @author: fuchen
 * @create: 2020-03-31 13:43
 **/
@Service
public class WatchServiceImpl implements WatchService {

    @Resource
    private WatchMapper watchMapper;

    public Integer queryByVideoCount(Integer videoId) {
        WatchCriteria watchCriteria = new WatchCriteria();
        watchCriteria.createCriteria().andVideoIdEqualTo(videoId);
        return watchMapper.countByExample(watchCriteria);
    }

    @Override
    public Watch getWatchByUserAndVideo(Integer userId, Integer videoId) {
        WatchCriteria watchCriteria = new WatchCriteria();
        watchCriteria.createCriteria().andUserIdEqualTo(userId).andVideoIdEqualTo(videoId);
        List<Watch> watchList = watchMapper.selectByExample(watchCriteria);
        return CollectionUtils.isEmpty(watchList) ? null : watchList.get(0);
    }

    @Override
    @Transactional
    public void addWatch(User user, Video video) {
        Watch watch = this.getWatchByUserAndVideo(user.getId(), video.getId());
        if (watch == null) {
            Watch newWatch = new Watch();
            newWatch.setUserId(user.getId());
            newWatch.setVideoId(video.getId());
            newWatch.setCreated(new Date());
            newWatch.setTime(1);
            watchMapper.insert(newWatch);
        }
    }

    @Override
    public void updateWatch(Watch watch) {
        watchMapper.updateByPrimaryKey(watch);
    }

}
