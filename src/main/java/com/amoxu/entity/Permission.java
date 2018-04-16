package com.amoxu.entity;

public class Permission {
    private Integer uid;

    private Integer friend;

    private Integer events;

    private Integer message;

    private Integer nearby;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getFriend() {
        return friend;
    }

    public void setFriend(Integer friend) {
        this.friend = friend;
    }

    public Integer getEvents() {
        return events;
    }

    public void setEvents(Integer events) {
        this.events = events;
    }

    public Integer getMessage() {
        return message;
    }

    public void setMessage(Integer message) {
        this.message = message;
    }

    public Integer getNearby() {
        return nearby;
    }

    public void setNearby(Integer nearby) {
        this.nearby = nearby;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "uid=" + uid +
                ", friend=" + friend +
                ", events=" + events +
                ", message=" + message +
                ", nearby=" + nearby +
                '}';
    }
}