package com.amoxu.entity;

import java.io.Serializable;
import java.util.Date;

public class Friends extends FriendsKey implements Serializable {


    private Date ctime;

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }


    public User getUser() {
        return user;
    }

    public Friends setUser(User user) {
        this.user = user;
        return this;
    }

    private User user;


    @Override
    public String toString() {
        return "Friends{" +
                "ctime=" + ctime +
                ", user=" + user +
                "} " + super.toString();
    }

}