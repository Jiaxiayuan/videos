package com.ies.controller;


import com.alibaba.fastjson.JSON;
import com.ies.constast.SysConstast;
import com.ies.domain.Log;
import com.ies.domain.User;
import com.ies.service.LogService;
import com.ies.service.UserService;
import com.ies.utils.DataGridView;
import com.ies.utils.ResultObj;
import com.ies.utils.WebUtils;
import com.ies.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;
    @Resource
    private LogService logService;

    @RequestMapping("loadAllUser")
    public DataGridView loadAllUser(UserVo uservo){
        return userService.queryAllUser(uservo);
    }
    @RequestMapping("addUser")
    public ResultObj addUser(UserVo uservo){
        User user = (User) WebUtils.getHttpSession().getAttribute("user");
        try{
            if(uservo.getId()!=null){
                uservo.setId(null);
            }
            uservo.setType(SysConstast.USER_TYPE_NORMAL);
            String pwd = DigestUtils.md5DigestAsHex(uservo.getPassword().getBytes());
            uservo.setPassword(pwd);
            userService.addUser(uservo);
            logService.addLog(new Log(user.getId(), "用户", "新增用户" + JSON.toJSONString(uservo), new Date()));
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            return ResultObj.ADD_ERROR;
        }
    }

    @RequestMapping("updateUser")
    public ResultObj updateUser(UserVo uservo){
        User user = (User) WebUtils.getHttpSession().getAttribute("user");
        try{
            userService.updateUser(uservo);
            logService.addLog(new Log(user.getId(), "用户", "更新用户" + JSON.toJSONString(uservo), new Date()));
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            return ResultObj.UPDATE_ERROR;
        }
    }


    @RequestMapping("deleteBatchUser")
    public ResultObj deleteBatchUser(UserVo uservo){
        User user = (User) WebUtils.getHttpSession().getAttribute("user");
        try{
            userService.deleteBatchRole(uservo.getIds());
            logService.addLog(new Log(user.getId(), "用户", "批量删除用户" + JSON.toJSONString(uservo), new Date()));
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            return ResultObj.DELETE_ERROR;
        }
    }

    @RequestMapping("deleteUser")
    public ResultObj deleteUser(UserVo uservo){
        User user = (User) WebUtils.getHttpSession().getAttribute("user");
        try{
            userService.deleteUser(uservo.getId());
            logService.addLog(new Log(user.getId(), "用户", "删除用户" + JSON.toJSONString(uservo), new Date()));
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            return ResultObj.DELETE_ERROR;
        }
    }


    @RequestMapping("resetUserPwd")
    public ResultObj resetUserPwd(Integer id){
        User user = (User) WebUtils.getHttpSession().getAttribute("user");
        try{
            userService.resetUserPwd(id);
            logService.addLog(new Log(user.getId(), "用户", "重置用户密码,userId: " + JSON.toJSONString(id), new Date()));
            return ResultObj.RESET_SUCCESS;
        }catch (Exception e){
            return ResultObj.RESET_ERROR;
        }
    }
}
