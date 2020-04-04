package com.ies.vo;

import com.ies.domain.VideosType;

/**
 * @program: ies
 * @description:
 * @author: fuchen
 * @create: 2020-03-28 17:53
 **/
public class VideosTypeVo extends VideosType {

    /**
     * 分页参数
     */
    private Integer page;
    private Integer limit;
    private Integer [] ids;
    private String name;

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

    public void setName(String name) {
        this.name = name;
    }
}
