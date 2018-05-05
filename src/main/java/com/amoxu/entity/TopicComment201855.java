package com.amoxu.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;
import java.util.List;

public class TopicComment201855 {
    private Integer cid;

    private Integer rcid;

    private Integer uid;

    private Integer ttid;

    private String content;

    private Integer likes;

    private Double feeling;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date ctime;

 /*   @JSONField(serialzeFeatures = SerializerFeature.WriteNullNumberAsZero)*/
    private Integer userLike;

    private String topic;

    private List<TopicComment201855> reply;
    private User sendUser;
    private User receiveUser;


    public Integer getCid() {
        return cid;
    }

    public TopicComment201855 setCid(Integer cid) {
        this.cid = cid;
        return this;
    }

    public Integer getRcid() {
        return rcid;
    }

    public TopicComment201855 setRcid(Integer rcid) {
        this.rcid = rcid;
        return this;
    }

    public Integer getUid() {
        return uid;
    }

    public TopicComment201855 setUid(Integer uid) {
        this.uid = uid;
        return this;
    }

    public Integer getTtid() {
        return ttid;
    }

    public TopicComment201855 setTtid(Integer ttid) {
        this.ttid = ttid;
        return this;
    }

    public String getContent() {
        return content;
    }

    public TopicComment201855 setContent(String content) {
        this.content = content;
        return this;
    }

    public Integer getLikes() {
        return likes;
    }

    public TopicComment201855 setLikes(Integer likes) {
        this.likes = likes;
        return this;
    }

    public Double getFeeling() {
        return feeling;
    }

    public TopicComment201855 setFeeling(Double feeling) {
        this.feeling = feeling;
        return this;
    }

    public Date getCtime() {
        return ctime;
    }

    public TopicComment201855 setCtime(Date ctime) {
        this.ctime = ctime;
        return this;
    }

    public Integer getUserLike() {
        return userLike;
    }

    public TopicComment201855 setUserLike(Integer userLike) {
        this.userLike = userLike;
        return this;
    }

    public String getTopic() {
        return topic;
    }

    public TopicComment201855 setTopic(String topic) {
        this.topic = topic;
        return this;
    }

    public List<TopicComment201855> getReply() {
        return reply;
    }

    public TopicComment201855 setReply(List<TopicComment201855> reply) {
        this.reply = reply;
        return this;
    }

    public User getSendUser() {
        return sendUser;
    }

    public TopicComment201855 setSendUser(User sendUser) {
        this.sendUser = sendUser;
        return this;
    }

    public User getReceiveUser() {
        return receiveUser;
    }

    public TopicComment201855 setReceiveUser(User receiveUser) {
        this.receiveUser = receiveUser;
        return this;
    }
}