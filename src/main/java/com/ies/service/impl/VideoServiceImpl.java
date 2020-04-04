package com.ies.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ies.domain.*;
import com.ies.mapper.CollectMapper;
import com.ies.mapper.VideoMapper;
import com.ies.mapper.VideoTypeBridgeMapper;
import com.ies.mapper.WatchMapper;
import com.ies.service.VideoService;
import com.ies.service.VideosTypeService;
import com.ies.utils.DataGridView;
import com.ies.vo.VideoVo;
import com.ies.vo.VideosTypeVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * @program: videos
 * @description:
 * @author: fuchen
 * @create: 2020-03-28 21:49
 **/
@Service
public class VideoServiceImpl implements VideoService {

    @Resource
    private VideoMapper videoMapper;
    @Resource
    private CollectMapper collectMapper;
    @Resource
    private WatchMapper watchMapper;
    @Resource
    private VideoTypeBridgeMapper videoTypeBridgeMapper;
    @Resource
    private VideosTypeService videosTypeService;

    @Override
    public DataGridView queryAllVideo(VideoVo videoVo) {
        VideoCriteria videosTypeCriteria = new VideoCriteria();
        VideoCriteria.Criteria criteria = videosTypeCriteria.createCriteria();
        if (!StringUtils.isEmpty(videoVo.getName())) {
            criteria.andNameLike("%" + videoVo.getName() + "%");
        }
        Page<Object> page = PageHelper.startPage(videoVo.getPage(), videoVo.getLimit());
        List<Video> videos = videoMapper.selectByExample(videosTypeCriteria);
        return new DataGridView(page.getTotal(), videos);
    }

    @Override
    public void addVideo(VideoVo videoVo) {
        videoMapper.insert(videoVo);
        dealVideoTypeBridge(videoVo);
    }

    private void dealVideoTypeBridge(VideoVo videoVo) {
        if (!StringUtils.isEmpty(videoVo.getTypes())) {
            String[] typeArr = videoVo.getTypes().split(",");
            List<VideoTypeBridge> add = new ArrayList<>();
            for (String type : typeArr) {
                VideosTypeVo videosTypeVo = new VideosTypeVo();
                videosTypeVo.setName(type);
                List<VideosType> videosTypes = videosTypeService.getByName(videosTypeVo);
                if (CollectionUtils.isEmpty(videosTypes)) {
                    videosTypeVo.setCreated(new Date());
                    videosTypeService.addVideosType(videosTypeVo);
                } else {
                    videosTypeVo.setId(videosTypes.get(0).getId());
                }
                VideoTypeBridge videoTypeBridge = new VideoTypeBridge();
                videoTypeBridge.setVideoId(videoVo.getId());
                videoTypeBridge.setVideoTypeId(videosTypeVo.getId());
                add.add(videoTypeBridge);
            }
            VideoTypeBridgeCriteria videoTypeBridgeCriteria = new VideoTypeBridgeCriteria();
            videoTypeBridgeCriteria.createCriteria().andVideoIdEqualTo(videoVo.getId());
            videoTypeBridgeMapper.deleteByExample(videoTypeBridgeCriteria);
            for (VideoTypeBridge videoTypeBridge : add) {
                videoTypeBridgeMapper.insert(videoTypeBridge);
            }
        }
    }

    @Override
    public void updateVideo(VideoVo videoVo) {
        dealVideoTypeBridge(videoVo);
        Video video = videoMapper.selectByPrimaryKey(videoVo.getId());
        video.setName(videoVo.getName());
        video.setDescs(videoVo.getDescs());
        videoMapper.updateByPrimaryKey(video);
    }

    @Override
    @Transactional
    public void deleteVideo(Integer id) {
        videoMapper.deleteByPrimaryKey(id);
        CollectCriteria collectCriteria = new CollectCriteria();
        collectCriteria.createCriteria().andVideoIdEqualTo(id);
        collectMapper.deleteByExample(collectCriteria);

        WatchCriteria watchCriteria = new WatchCriteria();
        watchCriteria.createCriteria().andVideoIdEqualTo(id);
        watchMapper.deleteByExample(watchCriteria);

        VideoTypeBridgeCriteria videoTypeBridgeCriteria = new VideoTypeBridgeCriteria();
        videoTypeBridgeCriteria.createCriteria().andVideoIdEqualTo(id);
        videoTypeBridgeMapper.deleteByExample(videoTypeBridgeCriteria);
    }

    @Override
    public void deleteBatchVideo(Integer[] ids) {
        for (Integer id : ids) {
            deleteVideo(id);
        }
    }

    @Override
    public Video selectVideo(Set<String> idSet, Integer[] exceptIds) {
        Set<Integer> newIdSet = new HashSet<>();
        for (String key : idSet) {
            newIdSet.add(Integer.parseInt(key));
        }
        return videoMapper.selectRandomVideo2(newIdSet, exceptIds);
    }

    @Override
    public Video selectVideo(String videoType, Integer[] exceptIds) {
        return videoMapper.selectRandomVideo3(videoType, exceptIds);
    }

    @Override
    public Video selectRandomVideo(Integer[] exceptIds) {
        return videoMapper.selectRandomVideo1(exceptIds);
    }

    public boolean findVideoTypeBridge(Integer videoId, Integer videoTypeId) {
        VideoTypeBridgeCriteria videoTypeBridgeCriteria = new VideoTypeBridgeCriteria();
        videoTypeBridgeCriteria.createCriteria().andVideoIdEqualTo(videoId).andVideoTypeIdEqualTo(videoTypeId);
        List<VideoTypeBridge> videoTypeBridges = videoTypeBridgeMapper.selectByExample(videoTypeBridgeCriteria);
        return !CollectionUtils.isEmpty(videoTypeBridges);
    }
}
