package com.amoxu.entity;

import java.util.Date;

public class Feature {
    private Integer fid;

    private String name;

    private Date ctime;

    private Double heat;

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Double getHeat() {
        return heat;
    }

    public void setHeat(Double heat) {
        this.heat = heat;
    }

    @Override
    public String toString() {
        return "Feature{" +
                "fid=" + fid +
                ", name='" + name + '\'' +
                ", ctime=" + ctime +
                ", heat=" + heat +
                '}';
    }
}