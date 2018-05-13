package com.amoxu.service;

import com.amoxu.exception.UnLoginException;

public interface ThumbsService {
    boolean likeEvents(Integer cid) throws UnLoginException;

    boolean likeComment(Integer cid) throws UnLoginException;

    boolean likeShare(Integer cid) throws UnLoginException;

    boolean likeTopic(Integer cid) throws UnLoginException;

    boolean likeShareComment(Integer cid) throws UnLoginException;

}
