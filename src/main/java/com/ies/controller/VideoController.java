package com.ies.controller;

import com.ies.domain.User;
import com.ies.domain.Video;
import com.ies.domain.VideosType;
import com.ies.service.CollectService;
import com.ies.service.VideoService;
import com.ies.service.VideosTypeService;
import com.ies.service.WatchService;
import com.ies.utils.DataGridView;
import com.ies.utils.ResultObj;
import com.ies.utils.WebUtils;
import com.ies.vo.VideoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: videos
 * @description:
 * @author: fuchen
 * @create: 2020-03-28 21:48
 **/
@RestController
@RequestMapping("video")
public class VideoController {

    @Resource
    private VideoService videoService;
    @Resource
    private VideosTypeService videosTypeService;
    @Resource
    private CollectService collectService;
    @Resource
    private WatchService watchService;

    @RequestMapping("loadAllVideo")
    public DataGridView loadAllVideo(VideoVo videoVo) {
        DataGridView dataGridView = videoService.queryAllVideo(videoVo);
        List<Video> videos = (List<Video>) dataGridView.getData();
        List<VideoVo> videoVos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(videos)) {
            videos.forEach(video -> {
                VideoVo _videoVo = new VideoVo();
                // 分类
                BeanUtils.copyProperties(video, _videoVo);
                List<VideosType> videoTypes = videosTypeService.getByVideoId(video.getId());
                StringBuilder sb = new StringBuilder();
                if (!CollectionUtils.isEmpty(videoTypes)) {
                    for (VideosType videosType : videoTypes) {
                        sb.append(videosType.getName());
                        sb.append(",");
                    }
                    String types = sb.substring(0, sb.length() - 1);
                    _videoVo.setTypes(types);
                }

                // 收藏数
                Integer collectCount = collectService.queryByVideoCount(video.getId());
                if (collectCount == null) {
                    collectCount = 0;
                }
                _videoVo.setCollected(collectCount);
                // 观看数
                Integer watchCount = watchService.queryByVideoCount(video.getId());
                if (watchCount == null) {
                    watchCount = 0;
                }
                _videoVo.setWatched(watchCount);
                videoVos.add(_videoVo);
            });
        }
        dataGridView.setData(videoVos);
        return dataGridView;
    }

    @RequestMapping("addVideo")
    public ResultObj addVideo(VideoVo videoVo) {
        User user = (User) WebUtils.getHttpSession().getAttribute("user");
        try {
            if (videoVo.getId() != null) {
                videoVo.setId(null);
            }
            videoVo.setCreated(new Date());
            videoVo.setUserId(user.getId());
            videoService.addVideo(videoVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            return ResultObj.ADD_ERROR;
        }
    }

    @RequestMapping("updateVideo")
    public ResultObj updateVideo(VideoVo videoVo) {
        try {
            videoService.updateVideo(videoVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            return ResultObj.UPDATE_ERROR;
        }
    }

    @RequestMapping("deleteVideo")
    public ResultObj deleteVideo(VideoVo videoVo) {
        try {
            videoService.deleteVideo(videoVo.getId());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            return ResultObj.DELETE_ERROR;
        }
    }

    @RequestMapping("deleteBatchVideo")
    public ResultObj deleteBatchVideo(VideoVo videoVo) {
        try {
            videoService.deleteBatchVideo(videoVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            return ResultObj.DELETE_ERROR;
        }
    }

}
