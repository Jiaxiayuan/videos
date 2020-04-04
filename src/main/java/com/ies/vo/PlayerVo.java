package com.ies.vo;

import com.ies.domain.Player;

/**
 * @program: game
 * @description: 选手页面类
 * @author: fuchen
 * @create: 2020-03-05 20:11
 **/
public class PlayerVo extends Player {

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
