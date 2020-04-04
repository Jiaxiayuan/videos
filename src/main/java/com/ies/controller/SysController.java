package com.ies.controller;

import com.ies.domain.City;
import com.ies.domain.Country;
import com.ies.domain.Course;
import com.ies.domain.School;
import com.ies.mapper.CourseMapper;
import com.ies.service.CityService;
import com.ies.service.CountryService;
import com.ies.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 页面跳转控制器
 */
@Controller
@RequestMapping("sys")
public class SysController {
    @Autowired
    private SchoolService schoolService;
    @Autowired
    private CityService cityService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private CourseMapper courseMapper;

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
