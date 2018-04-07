package com.amoxu.entity;

public class State {

    private Integer sid;

    private String name;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    @Override
    public String toString() {
        return "State{" +
                "sid=" + sid +
                ", name='" + name + '\'' +
                '}';
    }
}