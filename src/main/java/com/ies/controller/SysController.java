package com.ies.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面跳转控制器
 */
@Controller
@RequestMapping("sys")
public class SysController {

    /**
     * 跳转到菜单管理
     * @return
     */
    @RequestMapping("toMenuManager")
    public String toMenuManager(){
        return "system/menu/menuManager";
    }

    /**
     * 跳转到菜单管理左边的菜单树页面
     * @return
     */
    @RequestMapping("toMenuLeft")
    public String toMenuLeft(){
        return "system/menu/menuLeft";
    }

    /**
     * 跳转到菜单管理右边的菜单列表
     * @return
     */
    @RequestMapping("toMenuRight")
    public String toMenuRight(){
        return "system/menu/menuRight";
    }



    /**
     * 跳转到用户管理
     * @return
     */
    @RequestMapping("toUserManager")
    public String toUserManager(){
        return "system/user/userManager";
    }

    /**
     * 跳转到视频类别管理
     * @return
     */
    @RequestMapping("toVideosTypeManager")
    public String toVideosTypeManager(){
        return "system/videos/videosTypeManager";
    }

    /**
     * 跳转到视频管理
     * @return
     */
    @RequestMapping("toVideosManager")
    public String toVideosManager(){
        return "system/videos/videosManager";
    }

    /**
     * 跳转到收藏管理
     * @return
     */
    @RequestMapping("toCollectionManager")
    public String toCollectionManager(){
        return "system/collect/collectManager";
    }

    /**
     * 跳转到日志管理
     * @return
     */
    @RequestMapping("toLogManager")
    public String toLogManager(){
        return "system/log/logManager";
    }

    @RequestMapping("toWatchVideo")
    public String toWatchVideo() {
        return "system/client/watchVideo";
    }

}
