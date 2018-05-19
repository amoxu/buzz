package com.amoxu.entity;

import java.util.Date;

public class UserFeature {
    private Integer id;

    private Integer uid;

    private Integer fid;

    private Double counts;

    @Override
    public String toString() {
        return "UserFeature{" +
                "id=" + id +
                ", uid=" + uid +
                ", fid=" + fid +
                ", counts=" + counts +
                ", name='" + name + '\'' +
                ", ctime=" + ctime +
                ", atime=" + atime +
                '}';
    }

    private String name;

    private Date ctime;

    private Date atime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public Double getCounts() {
        return counts;
    }

    public void setCounts(Double counts) {
        this.counts = counts;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getAtime() {
        return atime;
    }

    public void setAtime(Date atime) {
        this.atime = atime;
    }

    public String getName() {
        return name;
    }

    public UserFeature setName(String name) {
        this.name = name;
        return this;
    }
}