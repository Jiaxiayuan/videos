package com.ies.controller;

import com.ies.domain.Major;
import com.ies.domain.SchoolMajor;
import com.ies.mapper.MajorMapper;
import com.ies.service.SchoolMajorService;
import com.ies.utils.DataGridView;
import com.ies.utils.ResultObj;
import com.ies.vo.SchoolMajorVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 学校专业管理控制器
 */
@RestController
@RequestMapping("schoolMajor")
public class SchoolMajorController {

    @Autowired
    private SchoolMajorService schoolMajorService;
    @Autowired
    private MajorMapper majorMapper;

    @RequestMapping("loadAllSchoolMajor")
    public DataGridView loadAllSchoolMajor(SchoolMajorVo schoolMajorVo){
        return schoolMajorService.queryAllSchoolMajor(schoolMajorVo);
    }

    @RequestMapping("addSchoolMajor")
    public ResultObj addSchoolMajor(SchoolMajorVo schoolMajorVo){
        try{
            if(schoolMajorVo.getId()!=null){
                schoolMajorVo.setId(null);
            }
            schoolMajorService.addSchoolMajor(schoolMajorVo);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            return ResultObj.ADD_ERROR;
        }
    }
    @RequestMapping("updateSchoolMajor")
    public ResultObj updateSchoolMajor(SchoolMajorVo schoolMajorvo){
        try{
            schoolMajorService.updateSchoolMajor(schoolMajorvo);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            return ResultObj.UPDATE_ERROR;
        }
    }
    @RequestMapping("deleteSchoolMajor")
    public ResultObj deleteSchoolMajor(SchoolMajorVo schoolMajorvo){
        try{
            schoolMajorService.deleteSchoolMajor(schoolMajorvo.getId());
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            return ResultObj.DELETE_ERROR;
        }
    }
    @RequestMapping("deleteBatchSchoolMajor")
    public ResultObj deleteBatchSchoolMajor(SchoolMajorVo schoolMajorvo){
        try{
            schoolMajorService.deleteBatchRole(schoolMajorvo.getIds());
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 显示树加载
     */
    @RequestMapping("loadSchoolMajorJson")
    public DataGridView loadSchoolMajorJson(SchoolMajorVo schoolMajorVo){
        List<SchoolMajor> list = this.schoolMajorService.selectBySchool(schoolMajorVo);
        for(SchoolMajor s:list){
            Major major = majorMapper.selectByPrimaryKey(s.getMajorid());
            s.setMajorname(major.getName());
        }
        return new DataGridView(list);
    }
}
