package com.ies.service;

import com.ies.utils.DataGridView;
import com.ies.vo.JudgmentVo;

public interface JudgmentService {

    DataGridView queryAllJudgment(JudgmentVo judgmentVo);

    void checkJudgment(JudgmentVo judgmentVo) throws Exception;

    void addJudgment(JudgmentVo judgmentVo);

    void updateJudgment(JudgmentVo judgmentVo);

    void deleteBatchJudgment(Integer[] ids);

    void deleteJudgment(Integer id);
}
