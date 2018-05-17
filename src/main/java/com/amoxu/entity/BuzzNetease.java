package com.amoxu.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class BuzzNetease {
    private Integer id;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date ctime;

    private String content;

    private Integer likedNum;

    private Integer songId;

    private Integer userId;

    private String userName;

    private String bereplied;

    private String keyword;

    /*================================================*/
    private List<Comments> comment;

    private Music music;

    private Singer singer;

    public List<Comments> getComment() {
        return comment;
    }

    public BuzzNetease setComment(List<Comments> comment) {
        this.comment = comment;
        return this;
    }

    public Music getMusic() {
        return music;
    }

    public BuzzNetease setMusic(Music music) {
        this.music = music;
        return this;
    }

    public Singer getSinger() {
        return singer;
    }

    public BuzzNetease setSinger(Singer singer) {
        this.singer = singer;
        return this;
    }

    public Integer getUserLike() {
        return userLike;
    }

    public BuzzNetease setUserLike(Integer userLike) {
        this.userLike = userLike;
        return this;
    }

    @Override
    public String toString() {
        return "BuzzNetease{" +
                "id=" + id +
                ", ctime=" + ctime +
                ", content='" + content + '\'' +
                ", likedNum=" + likedNum +
                ", songId=" + songId +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", bereplied='" + bereplied + '\'' +
                ", keyword='" + keyword + '\'' +
                ", comment=" + comment +
                ", music=" + music +
                ", singer=" + singer +
                ", userLike=" + userLike +
                '}';
    }

    @JSONField(serialzeFeatures = SerializerFeature.WriteNullNumberAsZero)
    private Integer userLike;/*用户是否点赞 >0 表示点赞了*/
    /*================================================*/


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getLikedNum() {
        return likedNum;
    }

    public void setLikedNum(Integer likedNum) {
        this.likedNum = likedNum;
    }

    public Integer getSongId() {
        return songId;
    }

    public void setSongId(Integer songId) {
        this.songId = songId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getBereplied() {
        return bereplied;
    }

    public void setBereplied(String bereplied) {
        this.bereplied = bereplied == null ? null : bereplied.trim();
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
    }
}