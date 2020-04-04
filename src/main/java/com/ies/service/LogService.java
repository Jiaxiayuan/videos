package com.ies.service;

import com.ies.domain.Log;
import com.ies.utils.DataGridView;
import com.ies.vo.LogVo;

public interface LogService {

    void addLog(Log log);

    DataGridView queryAllLog(LogVo logVo);
}
