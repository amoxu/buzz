package com.amoxu.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class Comments {
    private Integer cid;

    private Integer rcid;

    private Integer uid;

    private Integer mid;

    private String content;

    private Integer likes;

    private Double feeling;

    private Date ctime;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getRcid() {
        return rcid;
    }

    public void setRcid(Integer rcid) {
        this.rcid = rcid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Double getFeeling() {
        return feeling;
    }

    public void setFeeling(Double feeling) {
        this.feeling = feeling;
    }
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    @Override
    public String toString() {
        return "Comments{" +
                "cid=" + cid +
                ", rcid=" + rcid +
                ", uid=" + uid +
                ", mid=" + mid +
                ", content='" + content +
                ", likes=" + likes +
                ", feeling=" + feeling +
                ", ctime=" + ctime +
                ", user=" + user +
                '}';
    }

    private User user;

    public User getUser() {
        return user;
    }

    public Comments setUser(User user) {
        this.user = user;
        return this;
    }
}