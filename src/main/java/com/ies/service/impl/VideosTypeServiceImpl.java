package com.ies.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ies.domain.VideoTypeBridge;
import com.ies.domain.VideoTypeBridgeCriteria;
import com.ies.domain.VideosType;
import com.ies.domain.VideosTypeCriteria;
import com.ies.mapper.VideoTypeBridgeMapper;
import com.ies.mapper.VideosTypeMapper;
import com.ies.service.VideosTypeService;
import com.ies.utils.DataGridView;
import com.ies.vo.VideosTypeVo;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: ies
 * @description:
 * @author: fuchen
 * @create: 2020-03-28 17:49
 **/
@Service
public class VideosTypeServiceImpl implements VideosTypeService {

    @Resource
    private VideosTypeMapper videosTypeMapper;
    @Resource
    private VideoTypeBridgeMapper videoTypeBridgeMapper;

    @Override
    public DataGridView queryAllVideosType(VideosTypeVo videosTypeVo) {
        VideosTypeCriteria videosTypeCriteria = new VideosTypeCriteria();
        VideosTypeCriteria.Criteria criteria = videosTypeCriteria.createCriteria();
        if (!StringUtils.isEmpty(videosTypeVo.getName())) {
            criteria.andNameLike("%" + videosTypeVo.getName() + "%");
        }
        Page<Object> page = PageHelper.startPage(videosTypeVo.getPage(), videosTypeVo.getLimit());
        List<VideosType> videosTypes = videosTypeMapper.selectByExample(videosTypeCriteria);
        return new DataGridView(page.getTotal(), videosTypes);
    }

    @Override
    public List<VideosType> getByName(VideosTypeVo videosTypeVo) {
        VideosTypeCriteria videosTypeCriteria = new VideosTypeCriteria();
        videosTypeCriteria.createCriteria().andNameEqualTo(videosTypeVo.getName());
        List<VideosType> videosTypes = videosTypeMapper.selectByExample(videosTypeCriteria);
        return videosTypes;
    }

    @Override
    public void addVideosType(VideosTypeVo videosTypeVo) {
        videosTypeMapper.insertSelective(videosTypeVo);
    }

    @Override
    public void updateVideosType(VideosTypeVo videosTypeVo) {
        videosTypeMapper.updateByPrimaryKey(videosTypeVo);
    }

    @Override
    public void deleteVideosType(Integer id) {
        videosTypeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteBatchStudent(Integer[] ids) {
        for (Integer id : ids) {
            deleteVideosType(id);
        }
    }

    @Override
    public List<VideosType> getByVideoId(Integer id) {
        VideoTypeBridgeCriteria videoTypeBridgeCriteria = new VideoTypeBridgeCriteria();
        videoTypeBridgeCriteria.createCriteria().andVideoIdEqualTo(id);
        List<VideoTypeBridge> videoTypeBridges = videoTypeBridgeMapper.selectByExample(videoTypeBridgeCriteria);
        List<VideosType> videosTypes = new ArrayList<>();
        if (!CollectionUtils.isEmpty(videoTypeBridges)) {
            for (VideoTypeBridge videoTypeBridge : videoTypeBridges) {
                VideosType videosType = videosTypeMapper.selectByPrimaryKey(videoTypeBridge.getVideoTypeId());
                videosTypes.add(videosType);
            }
        }
        return videosTypes;
    }
}
