package com.ies.controller;

import com.ies.domain.Major;
import com.ies.service.MajorService;
import com.ies.utils.DataGridView;
import com.ies.utils.ResultObj;
import com.ies.vo.MajorVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 专业管理控制器
 */
@RestController
@RequestMapping("major")
public class MajorController {

    @Autowired
    private MajorService majorService;

    @RequestMapping("loadAllMajor")
    public DataGridView loadAllMajor(MajorVo majorVo){
        return majorService.queryAllMajor(majorVo);
    }
    @RequestMapping("addMajor")
    public ResultObj addMajor(MajorVo majorVo){
        try{
            if(majorVo.getId()!=null){
                majorVo.setId(null);
            }
            majorService.addMajor(majorVo);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            return ResultObj.ADD_ERROR;
        }
    }
    @RequestMapping("updateMajor")
    public ResultObj updateMajor(MajorVo majorvo){
        try{
            majorService.updateMajor(majorvo);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            return ResultObj.UPDATE_ERROR;
        }
    }
    @RequestMapping("deleteMajor")
    public ResultObj deleteMajor(MajorVo majorvo){
        try{
            majorService.deleteMajor(majorvo.getId());
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            return ResultObj.DELETE_ERROR;
        }
    }
    @RequestMapping("deleteBatchMajor")
    public ResultObj deleteBatchMajor(MajorVo majorvo){
        try{
            majorService.deleteBatchRole(majorvo.getIds());
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 显示树加载
     */
    @RequestMapping("loadAllMajorJson")
    public DataGridView loadAllMajorJson(){
        List<Major> list = this.majorService.selectAll();
        return new DataGridView(list);
    }



}
