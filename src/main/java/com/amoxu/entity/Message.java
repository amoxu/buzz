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


    @Override
    public String toString() {
        return "Message{" +
                "mid=" + mid +
                ", suid=" + suid +
                ", ruid=" + ruid +
                ", content='" + content + '\'' +
                ", ctime=" + ctime +
                ", sendUser=" + sendUser +
                ", receiveUser=" + receiveUser +
                '}';
    }

    private User sendUser;
    private User receiveUser;


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

    public User getReceiveUser() {
        return receiveUser;
    }

    public Message setReceiveUser(User receiveUser) {
        this.receiveUser = receiveUser;
        return this;
    }

    public User getSendUser() {
        return sendUser;
    }

    public Message setSendUser(User sendUser) {
        this.sendUser = sendUser;
        return this;
    }
}