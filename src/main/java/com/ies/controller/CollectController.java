package com.ies.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ies.domain.Collect;
import com.ies.domain.User;
import com.ies.domain.VideosType;
import com.ies.service.CollectService;
import com.ies.service.UserService;
import com.ies.service.VideosTypeService;
import com.ies.utils.DataGridView;
import com.ies.utils.ResultObj;
import com.ies.utils.WebUtils;
import com.ies.vo.CollectVo;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: videos
 * @description:
 * @author: fuchen
 * @create: 2020-04-04 09:32
 **/
@RestController
@RequestMapping("collect")
public class CollectController {

    @Resource
    private CollectService collectService;
    @Resource
    private UserService userService;
    @Resource
    private VideosTypeService videosTypeService;

    @RequestMapping("addCollect")
    public ResultObj addCollect(Integer videoId) {
        User user = (User) WebUtils.getHttpSession().getAttribute("user");
        List<VideosType> videosTypeList = videosTypeService.getByVideoId(videoId);
        Collect collect = collectService.queryByUserIdAndVideoId(user.getId(), videoId);
        User finalUser = userService.queryById(user.getId());
        // 获取偏好值
        JSONObject preference = new JSONObject();
        if (collect == null) {
            collectService.addCollect(user.getId(), videoId);
            if (!StringUtils.isEmpty(finalUser.getPreference())) {
                preference = JSON.parseObject(finalUser.getPreference());
                for (VideosType videosType : videosTypeList) {
                    if (preference.containsKey(videosType.getId().toString())) {
                        Integer count = preference.getInteger(videosType.getId().toString());
                        preference.put(videosType.getId().toString(), (count + 1));
                        break;
                    }
                }
            } else {

            }
        } else {
            collect.setEnableStatus(0);
            collectService.updateCollect(collect);
        }
        finalUser.setPreference(preference.toJSONString());
        userService.updateUser(finalUser);
        return ResultObj.ADD_SUCCESS;
    }

    @RequestMapping("loadAllCollect")
    public DataGridView loadAllCollect(CollectVo collectVo) {
        User user = (User) WebUtils.getHttpSession().getAttribute("user");
        if (user.getType() == 2) {
            collectVo.setUserId(user.getId());
            collectVo.setUsername(null);
        }
        return collectService.queryAllCollect(collectVo);
    }


}
