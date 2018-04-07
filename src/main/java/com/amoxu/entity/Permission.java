package com.amoxu.entity;

import java.util.Date;

public class Permission {
    private Integer uid;

    private Integer rid;

    private Date ctime;

    private Roles roles;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Roles getRoles() {
        return roles;
    }

    public Permission setRoles(Roles roles) {
        this.roles = roles;
        return this;
    }
}