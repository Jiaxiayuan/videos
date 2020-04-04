package com.ies.controller;

import com.alibaba.fastjson.JSON;
import com.ies.constast.SysConstast;
import com.ies.domain.Log;
import com.ies.domain.User;
import com.ies.domain.VideosType;
import com.ies.service.LogService;
import com.ies.service.VideosTypeService;
import com.ies.utils.DataGridView;
import com.ies.utils.ResultObj;
import com.ies.utils.WebUtils;
import com.ies.vo.VideosTypeVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @program: ies
 * @description: 视频类型
 * @author: fuchen
 * @create: 2020-03-28 16:07
 **/
@RestController
@RequestMapping("videosType")
public class VideosTypeController {

    @Resource
    private VideosTypeService videosTypeService;
    @Resource
    private LogService logService;

    @RequestMapping("loadAllVideosType")
    public DataGridView loadAllVideosType(VideosTypeVo videosTypeVo){
        return videosTypeService.queryAllVideosType(videosTypeVo);
    }

    @RequestMapping("addVideosType")
    public ResultObj addVideosType(VideosTypeVo videosTypeVo){
        User user = (User) WebUtils.getHttpSession().getAttribute("user");
        try{
            if(videosTypeVo.getId()!=null){
                videosTypeVo.setId(null);
            }
            List<VideosType> videosType = videosTypeService.getByName(videosTypeVo);
            if(videosType.size()>=1){
                return new ResultObj(SysConstast.CODE_ERROR, "视频类型已经存在");
            }
            videosTypeVo.setCreated(new Date());
            videosTypeService.addVideosType(videosTypeVo);
            logService.addLog(new Log(user.getId(), "视频类型", "新增视频类型" + JSON.toJSONString(videosTypeVo), new Date()));
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            return ResultObj.ADD_ERROR;
        }
    }
    @RequestMapping("updateVideosType")
    public ResultObj updateStudent(VideosTypeVo videosTypeVo){
        User user = (User) WebUtils.getHttpSession().getAttribute("user");
        try{
            List<VideosType> videosType = videosTypeService.getByName(videosTypeVo);
            if(videosType.size() >= 1){
                return new ResultObj(SysConstast.CODE_ERROR, "视频类型已经存在");
            }
            videosTypeService.updateVideosType(videosTypeVo);
            logService.addLog(new Log(user.getId(), "视频类型", "修改视频类型" + JSON.toJSONString(videosTypeVo), new Date()));
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            return ResultObj.UPDATE_ERROR;
        }
    }

    @RequestMapping("deleteVideosType")
    public ResultObj deleteVideosType(VideosTypeVo videosTypeVo){
        User user = (User) WebUtils.getHttpSession().getAttribute("user");
        try{
            videosTypeService.deleteVideosType(videosTypeVo.getId());
            logService.addLog(new Log(user.getId(), "视频类型", "删除视频类型" + JSON.toJSONString(videosTypeVo), new Date()));
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            return ResultObj.DELETE_ERROR;
        }
    }

    @RequestMapping("deleteBatchVideosType")
    public ResultObj deleteBatchStudent(VideosTypeVo videosTypeVo) {
        User user = (User) WebUtils.getHttpSession().getAttribute("user");
        try{
            videosTypeService.deleteBatchStudent(videosTypeVo.getIds());
            logService.addLog(new Log(user.getId(), "视频类型", "批量删除视频类型" + JSON.toJSONString(videosTypeVo), new Date()));
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            return ResultObj.DELETE_ERROR;
        }
    }

}
