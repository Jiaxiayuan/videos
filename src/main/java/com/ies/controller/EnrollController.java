package com.ies.controller;

import com.ies.service.EnrollService;
import com.ies.utils.DataGridView;
import com.ies.utils.ResultObj;
import com.ies.vo.EnrollVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 申请大学
 */
@RestController
@RequestMapping("enroll")
public class EnrollController {
    @Autowired
    private EnrollService enrollService;
    @RequestMapping("loadAllEnroll")
    public DataGridView loadAllEnroll(EnrollVo enrollVo) {
        return enrollService.queryAllEnroll(enrollVo);
    }

    @RequestMapping("addEnroll")
    public ResultObj addEnroll(Integer id){
        try{
            enrollService.addEnroll(id);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            return ResultObj.ADD_ERROR;
        }
    }

    @RequestMapping("deleteEnroll")
    public ResultObj deleteEnroll(Integer id){
        try{
            enrollService.deleteEnroll(id);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            return ResultObj.DELETE_ERROR;
        }
    }


}
