package com.ies.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ies.constast.SysConstast;
import com.ies.domain.*;
import com.ies.service.*;
import com.ies.utils.ResultObj;
import com.ies.utils.WebUtils;
import com.ies.vo.VideoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

/**
 * @program: videos
 * @description:
 * @author: fuchen
 * @create: 2020-04-02 01:55
 **/
@RestController
@RequestMapping("client")
public class ClientController {

    @Resource
    private WatchService watchService;
    @Resource
    private VideosTypeService videosTypeService;
    @Resource
    private UserService userService;
    @Resource
    private VideoService videoService;
    @Resource
    private CollectService collectService;

    @RequestMapping("clearSession")
    public ResultObj clearSession(String key) {
        WebUtils.getHttpSession().removeAttribute(key);
        return ResultObj.DELETE_SUCCESS;
    }

    @RequestMapping("fetchNextVideo")
    public ResultObj fetchNextVideo() {
        User user = (User) WebUtils.getHttpSession().getAttribute("user");
        String watched = (String) WebUtils.getHttpSession().getAttribute("watched");
        Integer[] exceptIds = null;
        if (!StringUtils.isEmpty(watched)) {
            exceptIds = new Integer[]{Integer.parseInt(watched)};
        }
        User finalUser = userService.queryById(user.getId());
        // 获取偏好值
        JSONObject preference;
        Video video = null;
        if (!StringUtils.isEmpty(finalUser.getPreference())) {
            preference = JSON.parseObject(finalUser.getPreference());
            if (!preference.isEmpty()) {
                // 如果存在偏好值
                Integer sum = 0;
                Set<String> idSet = preference.keySet();
                for (String key : idSet) {
                    Integer count = preference.getInteger(key);
                    sum += count;
                }
                Map<String, Double> ratioMap = new LinkedHashMap<>();
                for (String key : idSet) {
                    Integer count = preference.getInteger(key);
                    ratioMap.put(key, (count * 0.8D / sum ));
                }
                // 使用古典概率的思路决定选择哪一个类型
                // 这里对于偏好值类型全体按照80%的概率，不在偏好值类型按照20%的概率
                Double random = Math.random();
                // 判断这个random属于哪一段
                Double index = 0D;
                String finalType = null;
                for (String key : ratioMap.keySet()) {
                    Double value = ratioMap.get(key);
                    index += value;
                    if (random < index) {
                        // 说明在这个范围之内
                        finalType = key;
                        break;
                    }
                }

                if (finalType == null) {
                    // 说明不在偏好值之内,选择其他类型
                    video = videoService.selectVideo(idSet, exceptIds);
                } else {
                    video = videoService.selectVideo(finalType, exceptIds);
                }
            } else {
                video = videoService.selectRandomVideo(exceptIds);
            }
        } else {
            video = videoService.selectRandomVideo(exceptIds);
        }
        if (video == null) {
            return new ResultObj(SysConstast.CODE_ERROR, "暂无最新的视频");
        } else {
            watchService.addWatch(user, video);
            WebUtils.getHttpSession().setAttribute("watched", video.getId().toString());
            VideoVo videoVo = new VideoVo();
            BeanUtils.copyProperties(video, videoVo);
            Collect collect = collectService.queryByUserIdAndVideoId(user.getId(), video.getId());
            videoVo.setCollect(collect != null);
            return new ResultObj(SysConstast.CODE_SUCCESS, "SUCCESS", videoVo);
        }
    }

    @RequestMapping("checkLikeVideo")
    public ResultObj checkLikeVideo(Integer videoId) {
        User user = (User) WebUtils.getHttpSession().getAttribute("user");
        if (videoId == null) {
            return new ResultObj(SysConstast.CODE_ERROR, "参数错误");
        } else {
            Watch watch = watchService.getWatchByUserAndVideo(user.getId(), videoId);
            if (watch.getTime() % 3 == 0) {
                List<VideosType> videosTypeList = videosTypeService.getByVideoId(videoId);
                if (!CollectionUtils.isEmpty(videosTypeList)) {
                    User finalUser = userService.queryById(user.getId());
                    JSONObject preference = new JSONObject();
                    if (!StringUtils.isEmpty(finalUser.getPreference())) {
                        preference = JSON.parseObject(finalUser.getPreference());
                        for (VideosType videosType : videosTypeList) {
                            if (preference.containsKey(videosType.getId().toString())) {
                                Integer count = preference.getInteger(videosType.getId().toString());
                                preference.put(videosType.getId().toString(), (count + 1));
                                break;
                            } else {
                                preference.put(videosType.getId().toString(), 1);
                            }
                        }
                    }
                    finalUser.setPreference(preference.toJSONString());
                    userService.updateUser(finalUser);
                }
            }
            if (watch.getTime() < 30) {
                watch.setTime(watch.getTime() + 1);
                watchService.updateWatch(watch);
            }
            return new ResultObj(SysConstast.CODE_SUCCESS, "SUCCESS");
        }
    }

}
