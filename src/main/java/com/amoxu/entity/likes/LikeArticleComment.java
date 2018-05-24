package com.amoxu.entity.likes;

import java.util.Date;

public class LikeArticleComment extends LikeArticleCommentKey {
    private Date ctime;

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
}