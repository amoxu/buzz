package com.amoxu.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.Date;
import java.util.List;

public class MusicShareComment {
    private Integer cid;

    private Integer rcid;

    private Integer uid;


    private String content;

    private Integer likes;

    private Double feeling;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date ctime;

    private Integer baseCid;

    @JSONField(serialzeFeatures = SerializerFeature.WriteNullNumberAsZero)
    private Integer userLike;/*用户是否点赞 >0 表示点赞了*/


    private List<MusicShareComment> comment;/*回复列表*/
    private User sendUser;/*发送用户*/

    private User receiveUser;/*接收用户*/


    @Override
    public String toString() {
        return "MusicShareComment{" +
                "cid=" + cid +
                ", rcid=" + rcid +
                ", uid=" + uid +
                ", content='" + content + '\'' +
                ", likes=" + likes +
                ", feeling=" + feeling +
                ", ctime=" + ctime +
                ", baseCid=" + baseCid +
                ", userLike=" + userLike +
                ", comment=" + comment +
                ", sendUser=" + sendUser +
                ", receiveUser=" + receiveUser +
                '}';
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

    public Integer getBaseCid() {
        return baseCid;
    }

    public void setBaseCid(Integer baseCid) {
        this.baseCid = baseCid;
    }
    public Integer getUserLike() {
        return userLike;
    }

    public MusicShareComment setUserLike(Integer userLike) {
        this.userLike = userLike;
        return this;
    }

    public List<MusicShareComment> getComment() {


        return comment;
    }

    public MusicShareComment setComment(List<MusicShareComment> comment) {
        this.comment = comment;
        return this;
    }

    public User getSendUser() {
        return sendUser;
    }

    public MusicShareComment setSendUser(User sendUser) {
        this.sendUser = sendUser;
        return this;
    }

    public User getReceiveUser() {
        return receiveUser;
    }

    public MusicShareComment setReceiveUser(User receiveUser) {
        this.receiveUser = receiveUser;
        return this;
    }

}