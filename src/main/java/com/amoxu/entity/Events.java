package com.amoxu.entity;

import java.util.Date;

public class Events {
    private Integer cid;

    private Integer rcid;

    private Integer uid;

    private String content;

    private Integer likes;

    private Double feeling;

    private Date ctime;

    @Override
    public String toString() {
        return "Events{" +
                "cid=" + cid +
                ", rcid=" + rcid +
                ", uid=" + uid +
                ", content='" + content + '\'' +
                ", likes=" + likes +
                ", feeling=" + feeling +
                ", ctime=" + ctime +
                ", userLike=" + userLike +
                ", user=" + user +
                '}';
    }


    public Boolean getUserLike() {
        return userLike;
    }

    public Events setUserLike(Boolean userLike) {
        this.userLike = userLike;
        return this;
    }

    private Boolean userLike;

    public User getUser() {
        return user;
    }


    public Events setUser(User user) {
        this.user = user;
        return this;
    }

    private User user;

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

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }



}