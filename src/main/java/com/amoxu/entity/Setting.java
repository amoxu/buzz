package com.amoxu.entity;

public class Setting {
    private Integer sid;

    private Integer uid;

    private Integer comment;

    private Integer friend;

    private Boolean note;

    private Boolean nearby;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getComment() {
        return comment;
    }

    public void setComment(Integer comment) {
        this.comment = comment;
    }

    public Integer getFriend() {
        return friend;
    }

    public void setFriend(Integer friend) {
        this.friend = friend;
    }

    public Boolean getNote() {
        return note;
    }

    public void setNote(Boolean note) {
        this.note = note;
    }

    public Boolean getNearby() {
        return nearby;
    }

    public void setNearby(Boolean nearby) {
        this.nearby = nearby;
    }
}