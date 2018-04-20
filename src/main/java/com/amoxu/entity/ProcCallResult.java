package com.amoxu.entity;

public class ProcCallResult {

    @Override
    public String toString() {
        return "ProcCallResult{" +
                "cid=" + cid +
                ", uid=" + uid +
                '}';
    }

    public int getUid() {
        return uid;
    }

    public ProcCallResult  setUid(int uid) {
        this.uid = uid;
        return this;
    }

    public int getCid() {

        return cid;
    }

    public ProcCallResult  setCid(int cid) {
        this.cid = cid;
        return this;
    }

    private int cid;
    private int uid;

}
