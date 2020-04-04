package com.ies.service.impl;

import com.ies.domain.Collect;
import com.ies.domain.CollectCriteria;
import com.ies.domain.User;
import com.ies.domain.Video;
import com.ies.mapper.CollectMapper;
import com.ies.mapper.UserMapper;
import com.ies.mapper.VideoMapper;
import com.ies.service.CollectService;
import com.ies.utils.DataGridView;
import com.ies.vo.CollectVo;
import com.ies.vo.VideoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * @program: videos
 * @description:
 * @author: fuchen
 * @create: 2020-03-31 13:40
 **/
@Service
public class CollectServiceImpl implements CollectService {

    @Resource
    private CollectMapper collectMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private VideoMapper videoMapper;

    public Integer queryByVideoCount(Integer videoId) {
        CollectCriteria collectCriteria = new CollectCriteria();
        collectCriteria.createCriteria().andVideoIdEqualTo(videoId).andEnableStatusEqualTo(1);
        return collectMapper.countByExample(collectCriteria);
    }

    @Override
    public Collect queryByUserIdAndVideoId(Integer userId, Integer videoId) {
        CollectCriteria collectCriteria = new CollectCriteria();
        collectCriteria.createCriteria().andUserIdEqualTo(userId).andVideoIdEqualTo(videoId);
        List<Collect> collectList = collectMapper.selectByExample(collectCriteria);
        return CollectionUtils.isEmpty(collectList) ? null : collectList.get(0);
    }

    @Override
    public void addCollect(Integer userId, Integer videoId) {
        Collect add = new Collect();
        add.setUserId(userId);
        add.setVideoId(videoId);
        add.setCreated(new Date());
        add.setEnableStatus(1);
        collectMapper.insert(add);
    }

    @Override
    public void updateCollect(Collect collect) {
        collectMapper.updateByPrimaryKey(collect);
    }

    @Override
    public DataGridView queryAllCollect(CollectVo collectVo) {
        Long total = collectMapper.queryAllCollectCount(collectVo);
        List<CollectVo> collectVoList = new ArrayList<>();
        if (total != null && total > 0) {
            collectVo.setIndex((collectVo.getPage() - 1) * collectVo.getLimit());
            List<Collect> collectList = collectMapper.queryAllCollect(collectVo);
            Map<Integer, User> userMap = new HashMap<>();
            Map<Integer, Video> videoMap = new HashMap<>();
            for (Collect collect : collectList) {
                CollectVo _collectVo = new CollectVo();
                BeanUtils.copyProperties(collect, _collectVo);
                if (!userMap.containsKey(collect.getUserId())) {
                    User user = userMapper.selectByPrimaryKey(collect.getUserId());
                    _collectVo.setUsername(user.getUsername());
                    userMap.put(collect.getUserId(), user);
                } else {
                    User user = userMap.get(collect.getUserId());
                    _collectVo.setUsername(user.getUsername());
                }
                if (!videoMap.containsKey(collect.getVideoId())) {
                    Video video = videoMapper.selectByPrimaryKey(collect.getVideoId());
                    _collectVo.setVideoName(video.getName());
                    videoMap.put(collect.getVideoId(), video);
                } else {
                    Video video = videoMap.get(collect.getVideoId());
                    _collectVo.setVideoName(video.getName());
                }
                collectVoList.add(_collectVo);
            }
        }
        return new DataGridView(total, collectVoList);
    }

}
