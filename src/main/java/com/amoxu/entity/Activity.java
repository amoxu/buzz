package com.amoxu.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Activity {
    private Integer eid;

    private String name;

    private String content;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date ctime;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date stime;

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getStime() {
        return stime;
    }

    public void setStime(Date stime) {
        this.stime = stime;
    }
}