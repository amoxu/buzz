package com.amoxu.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Article {
    private Integer id;

    private Integer uid;

    private String name;
    @JSONField(format = "yyyy-MM-dd")
    private Date ctime;

    @JSONField(format = "yyyy-MM-dd")
    private Date stime;

    private String url;

    private String author;

    private Integer likes;

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", uid=" + uid +
                ", name='" + name + '\'' +
                ", ctime=" + ctime +
                ", stime=" + stime +
                ", url='" + url + '\'' +
                ", author='" + author + '\'' +
                ", likes=" + likes +
                ", userLike=" + userLike +
                ", content='" + content + '\'' +
                '}';
    }

    @JSONField(serialzeFeatures = SerializerFeature.WriteNullNumberAsZero)
    private Integer userLike;


    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCtime() {
        SimpleDateFormat ft =
                new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        return ft.format(ctime);
    }
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getStime() {
        return stime;
    }

    public void setStime(Date stime) {
        this.stime = stime;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getUrl() {
        url = "../article/" + id;
        return url;
    }

    public Article setUrl(String url) {
        this.url = url;
        return this;
    }

    public Integer getUserLike() {
        return userLike;
    }

    public Article setUserLike(Integer userLike) {
        this.userLike = userLike;
        return this;
    }
}