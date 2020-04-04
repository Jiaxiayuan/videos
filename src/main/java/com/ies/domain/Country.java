package com.ies.domain;

public class Country {
    private Integer id;

    private String number;

    private String imgage;

    private String competitiveness;

    private String visarequired;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public String getImgage() {
        return imgage;
    }

    public void setImgage(String imgage) {
        this.imgage = imgage == null ? null : imgage.trim();
    }

    public String getCompetitiveness() {
        return competitiveness;
    }

    public void setCompetitiveness(String competitiveness) {
        this.competitiveness = competitiveness == null ? null : competitiveness.trim();
    }

    public String getVisarequired() {
        return visarequired;
    }

    public void setVisarequired(String visarequired) {
        this.visarequired = visarequired == null ? null : visarequired.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}