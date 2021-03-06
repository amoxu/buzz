package com.amoxu.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Comments {
    private Integer cid;

    private Integer rcid;

    private Integer uid;

    private String content;

    private Integer likes;

    private Double feeling;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date ctime;

    private Integer buzzId;

    private User sendUser;
    private User receiveUser;

    @JSONField(serialzeFeatures = SerializerFeature.WriteNullNumberAsZero)
    private Integer userLike;/*用户是否点赞 >0 表示点赞了*/

    @Override
    public String toString() {
        return "Comments{" +
                "cid=" + cid +
                ", rcid=" + rcid +
                ", uid=" + uid +
                ", content='" + content + '\'' +
                ", likes=" + likes +
                ", feeling=" + feeling +
                ", ctime=" + ctime +
                ", buzzId=" + buzzId +
                ", sendUser=" + sendUser +
                ", receiveUser=" + receiveUser +
                ", userLike=" + userLike +
                '}';
    }


    public User getSendUser() {
        return sendUser;
    }

    public Comments setSendUser(User sendUser) {
        this.sendUser = sendUser;
        return this;
    }

    public Integer getUserLike() {
        return userLike;
    }

    public Comments setUserLike(Integer userLike) {
        this.userLike = userLike;
        return this;
    }


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

    public Integer getBuzzId() {
        return buzzId;
    }

    public void setBuzzId(Integer buzzId) {
        this.buzzId = buzzId;
    }

    public User getReceiveUser() {
        return receiveUser;
    }

    public Comments setReceiveUser(User receiveUser) {
        this.receiveUser = receiveUser;
        return this;
    }
}