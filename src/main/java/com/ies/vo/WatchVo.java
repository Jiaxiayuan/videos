package com.ies.vo;

import com.ies.domain.Watch;

import java.util.Date;

/**
 * @program: videos
 * @description:
 * @author: fuchen
 * @create: 2020-03-28 22:05
 **/
public class WatchVo extends Watch {

    /**
     * 分页参数
     */
    private Integer page;
    private Integer limit;
    private Integer [] ids;

    private Integer videoId;
    private String videoName;
    private Integer userId;
    private String userName;
    private Date created;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }

    @Override
    public Integer getVideoId() {
        return videoId;
    }

    @Override
    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    @Override
    public Date getCreated() {
        return created;
    }

    @Override
    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public Integer getUserId() {
        return userId;
    }

    @Override
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
