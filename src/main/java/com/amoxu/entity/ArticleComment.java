package com.amoxu.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.Date;

public class ArticleComment {
    private Integer id;

    private Integer baseId;

    private Integer uid;

    private String content;
    @JSONField(format = "yyyy-MM-dd")
    private Date ctime;

    private Integer raid;

    private Integer likes;
    @JSONField(serialzeFeatures = SerializerFeature.WriteNullNumberAsZero)
    private Integer userLike;/*用户是否点赞 >0 表示点赞了*/

    public Integer getUserLike() {
        return userLike;
    }

    public ArticleComment setUserLike(Integer userLike) {
        this.userLike = userLike;
        return this;
    }

    public User getSendUser() {
        return sendUser;
    }

    public ArticleComment setSendUser(User sendUser) {
        this.sendUser = sendUser;
        return this;
    }

    public User getReceiveUser() {
        return receiveUser;
    }

    public ArticleComment setReceiveUser(User receiveUser) {
        this.receiveUser = receiveUser;
        return this;
    }

    @Override
    public String toString() {
        return "ArticleComment{" +
                "id=" + id +
                ", baseId=" + baseId +
                ", uid=" + uid +
                ", content='" + content + '\'' +
                ", ctime=" + ctime +
                ", raid=" + raid +
                ", likes=" + likes +
                ", userLike=" + userLike +
                ", sendUser=" + sendUser +
                ", receiveUser=" + receiveUser +
                '}';
    }

    private User sendUser;
    private User receiveUser;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBaseId() {
        return baseId;
    }

    public void setBaseId(Integer baseId) {
        this.baseId = baseId;
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

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Integer getRaid() {
        return raid;
    }

    public void setRaid(Integer raid) {
        this.raid = raid;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }
}