package com.amoxu.service.impl;

import com.amoxu.entity.ProcCallResult;
import com.amoxu.entity.User;
import com.amoxu.mapper.likes.LikeCommentsMapper;
import com.amoxu.mapper.likes.LikeEventsMapper;
import com.amoxu.mapper.likes.LikeMusicShareMapper;
import com.amoxu.mapper.likes.LikeTopicCommentMapper;
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

    private ProcCallResult callResult = new ProcCallResult();
    private ProcCallResult likeParam(int cid) {
        Subject subject = SecurityUtils.getSubject();
        Integer uid = ((User) subject.getPrincipal()).getUid();

        callResult.setCid(cid);
        callResult.setUid(uid);
        return callResult;
    }
    @Override
    public boolean likeEvents(Integer cid) {
        /*点赞==1*//*取消点赞==0*/
        callResult = likeParam(cid);
        likeEventsMapper.callLikeProc(callResult);
        return callResult.getUid() == 1;
    }
    @Override
    public boolean likeComment(Integer cid) {
        return likeCommentsMapper.callLikeProc(likeParam(cid)).getUid() == 1;
    }

    @Override
    public boolean likeShare(Integer cid) {
        return likeMusicShareMapper.callLikeProc(likeParam(cid)).getUid() == 1;
    }

    @Override
    public boolean likeTopic(Integer cid) {
        return likeTopicCommentMapper.callLikeProc(likeParam(cid)).getUid() == 1;
    }
}
