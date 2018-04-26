package com.amoxu.entity;

public class Music {
    private Integer mid;
    private Integer sid;
    private Integer rid;
    private String name;
    private String album;
    private Singer singer;
    private Source source;
    private String link;

    @Override
    public String toString() {
        return "Music{" +
                "mid=" + mid +
                ", sid=" + sid +
                ", rid=" + rid +
                ", name='" + name + '\'' +
                ", album='" + album + '\'' +
                ", singer=" + singer +
                ", source=" + source +
                ", link='" + link + '\'' +
                '}';
    }


    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album == null ? null : album.trim();
    }

    public Singer getSinger() {
        return singer;
    }

    public Music setSinger(Singer singer) {
        this.singer = singer;
        return this;
    }

    public Source getSource() {
        return source;
    }

    public Music setSource(Source source) {
        this.source = source;
        return this;
    }

    public String getLink() {
        if (source.getName().equals("网易云")) {
            link = "http://music.163.com/song?id=" + sid;
        }
        return link;
    }

    public Music setLink(String link) {
        this.link = link;

        return this;
    }
}