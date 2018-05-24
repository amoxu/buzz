package com.amoxu.service.impl;

import com.amoxu.entity.ProcCallResult;
import com.amoxu.entity.User;
import com.amoxu.exception.UnLoginException;
import com.amoxu.mapper.likes.*;
import com.amoxu.service.ThumbsService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThumbsServiceImpl implements ThumbsService {
    @Autowired
    private LikeEventsMapper likeEventsMapper;
    @Autowired
    private LikeCommentsMapper likeCommentsMapper;
    @Autowired
    private LikeMusicShareMapper likeMusicShareMapper;
    @Autowired
    private LikeTopicCommentMapper likeTopicCommentMapper;
    @Autowired
    private LikeBuzzMapper likeBuzzMapper;

    @Autowired
    LikeArticleCommentMapper likeArticleCommentMapper;

    @Autowired
    LikeArticleMapper likeArticleMapper;


    @Autowired
    private LikeMusicShareCommentMapper likeMusicShareCommentMapper;

    private ProcCallResult callResult = new ProcCallResult();
    private ProcCallResult paramWraper(int cid) throws UnLoginException {
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            throw new UnLoginException();
        }
        Integer uid = ((User) subject.getPrincipal()).getUid();

        callResult.setCid(cid);
        callResult.setUid(uid);
        return callResult;
    }
    @Override
    public boolean likeEvents(Integer cid)  throws UnLoginException {
        /*点赞==1*//*取消点赞==0*/
        callResult = paramWraper(cid);
        likeEventsMapper.callLikeProc(callResult);
        return callResult.getUid() == 1;
    }
    @Override
    public boolean likeComment(Integer cid) throws UnLoginException {
        callResult = paramWraper(cid);
        likeCommentsMapper.callLikeProc(callResult);
        return callResult.getUid() == 1;
    }

    @Override
    public boolean likeShare(Integer cid) throws UnLoginException {
        callResult = paramWraper(cid);

        likeMusicShareMapper.callLikeProc(callResult);

        return callResult.getUid() == 1;
    }

    @Override
    public boolean likeTopic(Integer cid) throws UnLoginException {
        callResult = paramWraper(cid);
        likeTopicCommentMapper.callLikeProc(callResult);
        return callResult.getUid() == 1;
    }

    @Override
    public boolean likeShareComment(Integer cid) throws UnLoginException {
        callResult = paramWraper(cid);
        likeMusicShareCommentMapper.callLikeProc(callResult);
        return callResult.getUid() == 1;
    }

    @Override
    public boolean likeBuzz(Integer cid) throws UnLoginException {
        callResult = paramWraper(cid);
        likeBuzzMapper.callLikeProc(callResult);
        return callResult.getUid() == 1;
    }

    @Override
    public boolean likeArticleComment(Integer cid) throws UnLoginException {
        callResult = paramWraper(cid);
        likeArticleCommentMapper.callLikeProc(callResult);
        return callResult.getUid() == 1;
    }

    @Override
    public boolean likeArticle(Integer cid) throws UnLoginException {
        callResult = paramWraper(cid);
        likeArticleMapper.callLikeProc(callResult);
        return callResult.getUid() == 1;
    }
}
