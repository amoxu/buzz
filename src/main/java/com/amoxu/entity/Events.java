package com.amoxu.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;
import java.util.List;

public class Events {
    private Integer cid;

    private Integer rcid;

    private Integer uid;

    private String content;

    private Integer likes;

    private Double feeling;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date ctime;

    private List<Events> comment;


    public Boolean getUserLike() {
        return userLike;
    }

    public Events setUserLike(Boolean userLike) {
        this.userLike = userLike;
        return this;
    }

    private Boolean userLike;


    private User sendUser;

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
                ", comment=" + comment +
                ", userLike=" + userLike +
                ", sendUser=" + sendUser +
                ", receiveUser=" + receiveUser +
                '}';
    }

    private User receiveUser;

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


    public List<Events> getComment() {
        return comment;
    }

    public Events setComment(List<Events> comment) {
        this.comment = comment;
        return this;
    }

    public User getSendUser() {
        return sendUser;
    }

    public Events setSendUser(User sendUser) {
        this.sendUser = sendUser;
        return this;
    }

    public User getReceiveUser() {
        return receiveUser;
    }

    public Events setReceiveUser(User receiveUser) {
        this.receiveUser = receiveUser;
        return this;
    }
}