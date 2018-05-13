package com.amoxu.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.Date;
import java.util.List;

public class MusicShare {
    private Integer oid;

    private Integer mid;

    private Integer uid;

    private String content;

    private Integer likes;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date ctime;

    @JSONField(serialzeFeatures = SerializerFeature.WriteNullNumberAsZero)
    private Integer userLike;/*用户是否点赞 >0 表示点赞了*/

    private List<MusicShareComment> comment;/*回复列表*/
    private User sendUser;/*发送用户*/
    private Music music;/*分享的音乐*/


    public List<MusicShareComment> getComment() {
        return comment;
    }

    public MusicShare setComment(List<MusicShareComment> comment) {
        this.comment = comment;
        return this;
    }

    public User getSendUser() {
        return sendUser;
    }

    public MusicShare setSendUser(User sendUser) {
        this.sendUser = sendUser;
        return this;
    }

    public Integer getUserLike() {
        return userLike;
    }

    public MusicShare setUserLike(Integer userLike) {
        this.userLike = userLike;
        return this;
    }

    public Music getMusic() {
        return music;
    }

    public MusicShare setMusic(Music music) {
        this.music = music;
        return this;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
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

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    @Override
    public String toString() {
        return "MusicShare{" +
                "oid=" + oid +
                ", mid=" + mid +
                ", uid=" + uid +
                ", content='" + content + '\'' +
                ", likes=" + likes +
                ", ctime=" + ctime +
                ", userLike=" + userLike +
                ", comment=" + comment +
                ", sendUser=" + sendUser +
                ", music=" + music +
                '}';
    }
}