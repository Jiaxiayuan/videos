package com.ies.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ies.domain.Log;
import com.ies.domain.LogCriteria;
import com.ies.mapper.LogMapper;
import com.ies.service.LogService;
import com.ies.utils.DataGridView;
import com.ies.vo.LogVo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: videos
 * @description:
 * @author: fuchen
 * @create: 2020-03-28 20:01
 **/
@Service
public class LogServiceImpl implements LogService {

    @Resource
    private LogMapper logMapper;

    @Override
    public void addLog(Log log) {
        logMapper.insert(log);
    }

    @Override
    public DataGridView queryAllLog(LogVo logVo) {
        LogCriteria logCriteria = new LogCriteria();
        logCriteria.createCriteria();
        Page<Object> page = PageHelper.startPage(logVo.getPage(),logVo.getLimit());
        List<Log> logs = logMapper.selectByExample(logCriteria);
        return new DataGridView(page.getTotal(), logs);
    }
}
