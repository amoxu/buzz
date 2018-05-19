package com.amoxu.entity.likes;

public class LikeBuzzKey {
    private Integer buzzId;

    private Integer uid;

    public Integer getBuzzId() {
        return buzzId;
    }

    public LikeBuzzKey setBuzzId(Integer buzzId) {
        this.buzzId = buzzId;
        return this;
    }

    public Integer getUid() {
        return uid;
    }

    public LikeBuzzKey setUid(Integer uid) {
        this.uid = uid;
        return this;
    }
}