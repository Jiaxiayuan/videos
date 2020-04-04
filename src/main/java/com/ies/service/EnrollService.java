package com.ies.service;

import com.ies.utils.DataGridView;
import com.ies.vo.EnrollVo;

public interface EnrollService {
    void addEnroll(Integer id);

    DataGridView queryAllEnroll(EnrollVo enrollVo);

    void deleteEnroll(Integer id);
}
