package com.amoxu.service;

public interface ThumbsService {
    boolean likeEvents(Integer cid);

    boolean likeComment(Integer cid);

    boolean likeShare(Integer cid);

    boolean likeTopic(Integer cid);
}
