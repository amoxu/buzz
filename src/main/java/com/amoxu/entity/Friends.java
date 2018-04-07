package com.amoxu.entity;

import java.util.Date;

public class Friends extends FriendsKey {
    private Date ctime;

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
}