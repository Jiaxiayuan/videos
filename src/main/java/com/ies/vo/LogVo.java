package com.ies.vo;

import com.ies.domain.Log;

/**
 * @program: videos
 * @description:
 * @author: fuchen
 * @create: 2020-03-28 20:59
 **/
public class LogVo extends Log {

    private Integer page;
    private Integer limit;
    private Integer [] ids;
    private String username;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
