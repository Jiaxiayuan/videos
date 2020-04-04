package com.ies.vo;

import com.ies.domain.Video;

import java.util.List;

/**
 * @program: videos
 * @description:
 * @author: fuchen
 * @create: 2020-03-28 21:49
 **/
public class VideoVo extends Video {

    /**
     * 分页参数
     */
    private Integer page;
    private Integer limit;
    private Integer [] ids;
    private Integer [] videoTypes;
    private String name;
    private Integer userId;
    private String types;

    private Integer watched;

    private Integer collected;

    private Boolean collect;

    private List<CollectVo> collectVoList;
    private List<WatchVo> watchVoList;

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
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Integer getUserId() {
        return userId;
    }

    @Override
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getWatched() {
        return watched;
    }

    public void setWatched(Integer watched) {
        this.watched = watched;
    }

    public Integer getCollected() {
        return collected;
    }

    public void setCollected(Integer collected) {
        this.collected = collected;
    }

    public List<CollectVo> getCollectVoList() {
        return collectVoList;
    }

    public void setCollectVoList(List<CollectVo> collectVoList) {
        this.collectVoList = collectVoList;
    }

    public List<WatchVo> getWatchVoList() {
        return watchVoList;
    }

    public void setWatchVoList(List<WatchVo> watchVoList) {
        this.watchVoList = watchVoList;
    }

    public Integer[] getVideoTypes() {
        return videoTypes;
    }

    public void setVideoTypes(Integer[] videoTypes) {
        this.videoTypes = videoTypes;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public Boolean getCollect() {
        return collect;
    }

    public void setCollect(Boolean collect) {
        this.collect = collect;
    }
}
