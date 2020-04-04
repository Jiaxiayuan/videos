package com.ies.service;

import com.ies.domain.VideosType;
import com.ies.utils.DataGridView;
import com.ies.vo.VideosTypeVo;

import java.util.List;

public interface VideosTypeService {


    DataGridView queryAllVideosType(VideosTypeVo videosTypeVo);

    List<VideosType> getByName(VideosTypeVo videosTypeVo);

    void addVideosType(VideosTypeVo videosTypeVo);

    void updateVideosType(VideosTypeVo videosTypeVo);

    void deleteVideosType(Integer id);

    void deleteBatchStudent(Integer[] ids);

    List<VideosType> getByVideoId(Integer id);
}
