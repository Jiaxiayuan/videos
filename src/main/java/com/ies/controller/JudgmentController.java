package com.ies.controller;

import com.ies.constast.SysConstast;
import com.ies.service.JudgmentService;
import com.ies.utils.DataGridView;
import com.ies.utils.ResultObj;
import com.ies.vo.JudgmentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: game
 * @description: 裁判控制器
 * @author: fuchen
 * @create: 2020-03-06 22:10
 **/
@RestController
@RequestMapping("judgment")
public class JudgmentController {

    @Autowired
    private JudgmentService judgmentService;

    @RequestMapping("loadAllJudgment")
    public DataGridView loadAllJudgment(JudgmentVo judgmentVo) {
        return judgmentService.queryAllJudgment(judgmentVo);
    }

    @RequestMapping("addJudgment")
    public ResultObj addJudgment(JudgmentVo judgmentVo) {
        try {
            if (judgmentVo.getId() != null) {
                judgmentVo.setId(null);
            }
            judgmentService.checkJudgment(judgmentVo);
            judgmentService.addJudgment(judgmentVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            return new ResultObj(SysConstast.CODE_ERROR, e.getMessage());
        }
    }

    @RequestMapping("updateJudgment")
    public ResultObj updateJudgment(JudgmentVo judgmentVo) {
        try {
            judgmentService.updateJudgment(judgmentVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            return ResultObj.UPDATE_ERROR;
        }
    }

    @RequestMapping("deleteBatchJudgment")
    public ResultObj deleteBatchJudgment(JudgmentVo judgmentVo) {
        try {
            judgmentService.deleteBatchJudgment(judgmentVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            return ResultObj.DELETE_ERROR;
        }
    }

    @RequestMapping("deleteJudgment")
    public ResultObj deleteJudgment(JudgmentVo judgmentVo) {
        try {
            judgmentService.deleteJudgment(judgmentVo.getId());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            return ResultObj.DELETE_ERROR;
        }
    }

}
