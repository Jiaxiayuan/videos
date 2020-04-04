package com.ies.controller;

import com.ies.service.MajorCourseService;
import com.ies.utils.DataGridView;
import com.ies.utils.ResultObj;
import com.ies.vo.MajorCourseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 学校专业管理控制器
 */
@RestController
@RequestMapping("majorCourse")
public class MajorCourseController {

    @Autowired
    private MajorCourseService majorCourseService;

    @RequestMapping("loadAllMajorCourse")
    public DataGridView loadAllMajorCourse(MajorCourseVo majorCourseVo){
        return majorCourseService.queryAllMajorCourse(majorCourseVo);
    }

    @RequestMapping("addMajorCourse")
    public ResultObj addMajorCourse(MajorCourseVo majorCourseVo){
        try{
            if(majorCourseVo.getId()!=null){
                majorCourseVo.setId(null);
            }
            majorCourseService.addMajorCourse(majorCourseVo);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            return ResultObj.ADD_ERROR;
        }
    }
    @RequestMapping("updateMajorCourse")
    public ResultObj updateMajorCourse(MajorCourseVo majorCoursevo){
        try{
            majorCourseService.updateMajorCourse(majorCoursevo);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            return ResultObj.UPDATE_ERROR;
        }
    }
    @RequestMapping("deleteMajorCourse")
    public ResultObj deleteMajorCourse(MajorCourseVo majorCoursevo){
        try{
            majorCourseService.deleteMajorCourse(majorCoursevo.getId());
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            return ResultObj.DELETE_ERROR;
        }
    }
    @RequestMapping("deleteBatchMajorCourse")
    public ResultObj deleteBatchMajorCourse(MajorCourseVo majorCoursevo){
        try{
            majorCourseService.deleteBatchRole(majorCoursevo.getIds());
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            return ResultObj.DELETE_ERROR;
        }
    }
}
