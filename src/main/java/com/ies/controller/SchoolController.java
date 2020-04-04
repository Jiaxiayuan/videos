package com.ies.controller;

import com.ies.domain.School;
import com.ies.service.SchoolService;
import com.ies.utils.DataGridView;
import com.ies.utils.ResultObj;
import com.ies.vo.SchoolVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 学校管理控制器
 */
@RestController
@RequestMapping("school")
public class SchoolController {

    @Autowired
    private SchoolService schoolService;

    @RequestMapping("loadAllSchool")
    public DataGridView loadAllSchool(SchoolVo schoolVo){
        return schoolService.queryAllSchool(schoolVo);
    }

    @RequestMapping("addSchool")
    public ResultObj addSchool(SchoolVo schoolVo){
        try{
            if(schoolVo.getId()!=null){
                schoolVo.setId(null);
            }
            schoolService.addSchool(schoolVo);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            return ResultObj.ADD_ERROR;
        }
    }
    @RequestMapping("updateSchool")
    public ResultObj updateSchool(SchoolVo schoolvo){
        try{
            schoolService.updateSchool(schoolvo);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            return ResultObj.UPDATE_ERROR;
        }
    }
    @RequestMapping("deleteSchool")
    public ResultObj deleteSchool(SchoolVo schoolvo){
        try{
            schoolService.deleteSchool(schoolvo.getId());
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            return ResultObj.DELETE_ERROR;
        }
    }
    @RequestMapping("deleteBatchSchool")
    public ResultObj deleteBatchSchool(SchoolVo schoolvo){
        try{
            schoolService.deleteBatchRole(schoolvo.getIds());
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 显示树加载
     */
    @RequestMapping("loadAllSchoolJson")
    public DataGridView loadAllSchoolJson(){
        List<School> list = this.schoolService.selectAll();
        return new DataGridView(list);
    }
    /**
     * 显示树加载
     */
    @RequestMapping("loadSchoolJson")
    public DataGridView loadSchoolJson(SchoolVo schoolVo){
        List<School> list = this.schoolService.selectByCity(schoolVo);
        return new DataGridView(list);
    }

}
