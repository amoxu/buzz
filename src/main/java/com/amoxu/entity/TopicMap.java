package com.amoxu.entity;

public class TopicMap {
    public TopicMap(Topic topic) {
        setTid(topic.getTid());
        setTopic(topic.getTopic());
    }
    private Integer tid;
    private String topic;

    @Override
    public String toString() {
        return "TopicMap{" +
                "tid=" + tid +
                ", topic='" + topic + '\'' +
                '}';
    }

    public Integer getTid() {
        return tid;
    }

    public TopicMap setTid(Integer tid) {
        this.tid = tid;
        return this;
    }

    public String getTopic() {
        return topic;
    }

    public TopicMap setTopic(String topic) {
        this.topic = topic;
        return this;
    }
}
