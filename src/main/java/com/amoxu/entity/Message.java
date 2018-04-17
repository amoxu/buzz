package com.amoxu.entity;

import java.util.Date;

public class Message {
    /*消息ID*/
    private Integer mid;

    /*sendID，发送方ID*/
    private Integer suid;
    /*receiveID， 接收方ID*/
    private Integer ruid;

    private String content;

    private Date ctime;

    public User getUser() {
        return user;
    }

    public Message setUser(User user) {
        this.user = user;
        return this;
    }

    @Override
    public String toString() {
        return "Message{" +
                "mid=" + mid +
                ", suid=" + suid +
                ", ruid=" + ruid +
                ", content='" + content + '\'' +
                ", ctime=" + ctime +
                ", user=" + user +
                '}';
    }

    private User user;


    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public Integer getSuid() {
        return suid;
    }

    public void setSuid(Integer suid) {
        this.suid = suid;
    }

    public Integer getRuid() {
        return ruid;
    }

    public void setRuid(Integer ruid) {
        this.ruid = ruid;
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
}