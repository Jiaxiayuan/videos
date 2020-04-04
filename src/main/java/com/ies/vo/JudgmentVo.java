package com.ies.vo;

import com.ies.domain.Judgment;

/**
 * @program: game
 * @description: 裁判VO
 * @author: fuchen
 * @create: 2020-03-06 22:14
 **/
public class JudgmentVo extends Judgment {

    private String username;
    private String password;

    /**
     * 分页参数
     */
    private Integer page;
    private Integer limit;
    private Integer [] ids;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
