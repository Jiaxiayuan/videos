package com.ies.vo;

import com.ies.domain.School;

public class SchoolVo extends School {
    private Integer page;
    private Integer limit;
    private Integer [] ids;
    private String competitiveness;
    private String visarequired;
    private String citytype;


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

    public String getCompetitiveness() {
        return competitiveness;
    }

    public void setCompetitiveness(String competitiveness) {
        this.competitiveness = competitiveness;
    }

    public String getVisarequired() {
        return visarequired;
    }

    public void setVisarequired(String visarequired) {
        this.visarequired = visarequired;
    }

    public String getCitytype() {
        return citytype;
    }

    public void setCitytype(String citytype) {
        this.citytype = citytype;
    }
}
