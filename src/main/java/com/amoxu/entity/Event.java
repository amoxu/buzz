package com.amoxu.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.Date;
import java.util.List;

public class Event {
    private Integer cid;

    private Integer rcid;

    private Integer uid;

    private String content;

    private Integer likes;

    private Double feeling;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date ctime;

    private Integer bid;



    @JSONField(serialzeFeatures = SerializerFeature.WriteNullBooleanAsFalse)
    private Boolean userLike;
    private User sendUser;
    private List<Event> comment;
    private User receiveUser;

    @JSONField(serialize = false)
    private Integer onlineId;


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

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }
    public Boolean getUserLike() {
        return userLike;
    }

    public Event setUserLike(Boolean userLike) {
        this.userLike = userLike;
        return this;
    }

    public User getSendUser() {
        return sendUser;
    }

    public Event setSendUser(User sendUser) {
        this.sendUser = sendUser;
        return this;
    }

    public User getReceiveUser() {
        return receiveUser;
    }

    public Event setReceiveUser(User receiveUser) {
        this.receiveUser = receiveUser;
        return this;
    }

    public List<Event> getComment() {

        return comment;
    }

    public Event setComment(List<Event> comment) {
        this.comment = comment;
        return this;
    }

    @Override
    public String toString() {
        return "Event{" +
                "cid=" + cid +
                ", rcid=" + rcid +
                ", uid=" + uid +
                ", content='" + content + '\'' +
                ", likes=" + likes +
                ", feeling=" + feeling +
                ", ctime=" + ctime +
                ", bid=" + bid +
                ", userLike=" + userLike +
                ", sendUser=" + sendUser +
                ", comment=" + comment +
                ", receiveUser=" + receiveUser +
                '}';
    }

    public Integer getOnlineId() {
        return onlineId;
    }

    public Event setOnlineId(Integer onlineId) {
        this.onlineId = onlineId;
        return this;
    }
}