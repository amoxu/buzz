package com.amoxu.entity;

public class FriendsKey {
    private Integer suid;

    private Integer duid;

    public Integer getSuid() {
        return suid;
    }

    public FriendsKey setSuid(Integer suid) {
        this.suid = suid;
        return this;
    }

    public Integer getDuid() {
        return duid;
    }

    public FriendsKey setDuid(Integer duid) {
        this.duid = duid;
        return this;
    }

    @Override
    public String toString() {
        return "FriendsKey{" +
                "suid=" + suid +
                ", duid=" + duid +
                '}';
    }
}