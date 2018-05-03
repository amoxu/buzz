package com.amoxu.entity;


import com.alibaba.fastjson.annotation.JSONField;
import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;

import java.io.Serializable;
import java.util.Date;

public class User implements Cloneable, Serializable {
    @Override
    public User clone() {
        User u = new User();
        RuntimeSchema<User> schema = RuntimeSchema.createFrom(User.class);
        byte[] bytes = ProtostuffIOUtil.toByteArray(this, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
        ProtostuffIOUtil.mergeFrom(bytes, u, schema);
        return u;
    }

    @Override
    public String toString() {

        return "User{" +
                "uid=" + uid +
                ", nickname='" + nickname + '\'' +
                ", rid=" + rid +
                ", icons='" + icons + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", birth=" + birth +
                ", city='" + city + '\'' +
                ", state=" + state +
                ", ctime=" + ctime +
                ", introduce='" + introduce + '\'' +
                ", note='" + note + '\'' +
                ", userState=" + userState +
                ", roles=" + roles +
                '}';
    }

    private Integer uid;

    private String nickname;
    @JSONField(serialize = false)
    private Integer rid;

    private String icons;
    private String email;
    @JSONField(serialize = false)
    private String password;

    private String sex;

    private Date birth;

    private String city;
    @JSONField(serialize = false)
    private Integer state;

    private Date ctime;

    private String introduce;
    @JSONField(serialize = false)
    private String note;

    public State getUserState() {
        return userState;
    }

    public User setUserState(State userState) {
        this.userState = userState;
        return this;
    }

    public Roles getRoles() {
        return roles;
    }

    public User setRoles(Roles roles) {
        this.roles = roles;
        return this;
    }

    @JSONField(serialize = false)
    private State userState = null;
    @JSONField(serialize = false)
    private Roles roles = null;


    public Integer getUid() {
        return uid;
    }

    public User setUid(Integer uid) {
        this.uid = uid;
        return this;

    }

    public String getNickname() {
        return nickname;
    }

    public User setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
        return this;

    }

    public Integer getRid() {
        return rid;
    }

    public User setRid(Integer rid) {
        this.rid = rid;
        return this;

    }

    public String getIcons() {
        return icons;
    }

    public User setIcons(String icons) {
        this.icons = icons == null ? null : icons.trim();
        return this;

    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email == null ? null : email.trim();
        return this;

    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password == null ? null : password.trim();
        return this;
    }

    public String getSex() {
        return sex;
    }

    public User setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
        return this;

    }

    public Date getBirth() {
        return birth;
    }

    public User setBirth(Date birth) {
        this.birth = birth;
        return this;

    }

    public String getCity() {
        return city;
    }

    public User setCity(String city) {
        this.city = city == null ? null : city.trim();
        return this;

    }

    public Integer getState() {
        return state;
    }

    public User setState(Integer state) {
        this.state = state;
        return this;

    }

    public Date getCtime() {
        return ctime;
    }

    public User setCtime(Date ctime) {
        this.ctime = ctime;
        return this;

    }

    public String getIntroduce() {
        return introduce;
    }

    public User setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
        return this;

    }

    public String getNote() {
        return note;
    }

    public User setNote(String note) {
        this.note = note == null ? null : note.trim();
        return this;

    }
}