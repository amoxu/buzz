package com.amoxu.entity.likes;

import java.util.Date;

public class LikeEvents extends LikeEventsKey {
    private Date ctime;

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
}