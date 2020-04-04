package com.ies.service;

import com.ies.domain.Video;
import com.ies.utils.DataGridView;
import com.ies.vo.VideoVo;

import java.util.Set;

public interface VideoService {
    DataGridView queryAllVideo(VideoVo videoVo);

    void addVideo(VideoVo videoVo);

    void updateVideo(VideoVo videoVo);

    void deleteVideo(Integer id);

    void deleteBatchVideo(Integer[] ids);

    Video selectVideo(Set<String> nameSet, Integer[] exceptIds);

    Video selectVideo(String videoType, Integer[] exceptIds);

    Video selectRandomVideo(Integer[] exceptIds);
}
