package com.ies.controller;

import com.ies.domain.Course;
import com.ies.service.CourseService;
import com.ies.utils.DataGridView;
import com.ies.utils.ResultObj;
import com.ies.vo.CourseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 专业管理控制器
 */
@RestController
@RequestMapping("course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @RequestMapping("loadAllCourse")
    public DataGridView loadAllCourse(CourseVo courseVo){
        return courseService.queryAllCourse(courseVo);
    }
    @RequestMapping("addCourse")
    public ResultObj addCourse(CourseVo courseVo){
        try{
            if(courseVo.getId()!=null){
                courseVo.setId(null);
            }
            courseService.addCourse(courseVo);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            return ResultObj.ADD_ERROR;
        }
    }
    @RequestMapping("updateCourse")
    public ResultObj updateCourse(CourseVo coursevo){
        try{
            courseService.updateCourse(coursevo);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            return ResultObj.UPDATE_ERROR;
        }
    }
    @RequestMapping("deleteCourse")
    public ResultObj deleteCourse(CourseVo coursevo){
        try{
            courseService.deleteCourse(coursevo.getId());
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            return ResultObj.DELETE_ERROR;
        }
    }
    @RequestMapping("deleteBatchCourse")
    public ResultObj deleteBatchCourse(CourseVo coursevo){
        try{
            courseService.deleteBatchRole(coursevo.getIds());
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 显示树加载
     */
    @RequestMapping("loadAllCourseJson")
    public DataGridView loadAllCourseJson(){
        List<Course> list = this.courseService.selectAll();
        return new DataGridView(list);
    }


}
