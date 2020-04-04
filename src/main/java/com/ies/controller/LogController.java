package com.ies.controller;

import com.ies.domain.Log;
import com.ies.domain.User;
import com.ies.service.LogService;
import com.ies.service.UserService;
import com.ies.utils.DataGridView;
import com.ies.vo.LogVo;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: videos
 * @description:
 * @author: fuchen
 * @create: 2020-03-28 20:58
 **/
@RestController
@RequestMapping("log")
public class LogController {

    @Resource
    private LogService logService;
    @Resource
    private UserService userService;

    @RequestMapping("loadAllLog")
    public DataGridView loadAllLog(LogVo logVo){
        DataGridView dataGridView = logService.queryAllLog(logVo);
        List<Log> logs = (List<Log>) dataGridView.getData();
        List<LogVo> logVos = new ArrayList<>();
        for (Log log : logs) {
            LogVo _log = new LogVo();
            BeanUtils.copyProperties(log, _log);
            User user = userService.queryById(log.getUserId());
            if (user != null) {
                _log.setUsername(user.getUsername());
            } else {
                _log.setUsername(log.getUserId().toString());
            }
            logVos.add(_log);
        }
        dataGridView.setData(logVos);
        return dataGridView;
    }

}
