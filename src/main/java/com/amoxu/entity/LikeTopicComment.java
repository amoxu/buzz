package com.amoxu.entity;

import java.util.Date;

public class LikeTopicComment extends LikeTopicCommentKey {
    private Date ctime;

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
}