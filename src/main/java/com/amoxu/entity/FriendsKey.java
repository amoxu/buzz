package com.amoxu.entity;

public class FriendsKey {
    private Integer suid;

    private Integer duid;

    public Integer getSuid() {
        return suid;
    }

    public void setSuid(Integer suid) {
        this.suid = suid;
    }

    public Integer getDuid() {
        return duid;
    }

    public void setDuid(Integer duid) {
        this.duid = duid;
    }

    @Override
    public String toString() {
        return "FriendsKey{" +
                "suid=" + suid +
                ", duid=" + duid +
                '}';
    }
}