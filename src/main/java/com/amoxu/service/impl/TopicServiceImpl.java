package com.amoxu.service.impl;

import com.amoxu.entity.*;
import com.amoxu.exception.UnLoginException;
import com.amoxu.mapper.FriendsMapper;
import com.amoxu.mapper.TopicCommentMapper;
import com.amoxu.mapper.TopicMapper;
import com.amoxu.service.TopicService;
import com.amoxu.util.StaticEnum;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {
    @Autowired
    private TopicCommentMapper commentMapper;
    @Autowired
    private TopicMapper topicMapper;

    @Autowired
    private  FriendsMapper friendsMapper;

    @Override
    public PageResult<TopicComment> getMain(String type, PageResult<TopicComment> pageResult) throws UnLoginException {
        TopicCommentExample commentExample = new TopicCommentExample();
        TopicCommentExample.Criteria commentExampleCriteria = commentExample.createCriteria();

        commentExampleCriteria.andBaseCidEqualTo(0);
        Subject subject = SecurityUtils.getSubject();
        boolean authenticated = subject.isAuthenticated();

        switch (type) {
            case "hot":
                commentExample.setOrderByClause("likes");
                break;
            case "new":
                commentExample.setOrderByClause("ctime");
                break;
            case "rand":
                commentExample.setOrderByClause("rand()");
                break;
            case "focus":
                if (!authenticated) {
                    throw new UnLoginException();
                }
                /*获取好友ID*/
                User u = (User) subject.getPrincipal();
                Integer uid = u.getUid();
                FriendsExample friendsExample = new FriendsExample();
                FriendsExample.Criteria friendsExampleCriteria = friendsExample.createCriteria();
                friendsExampleCriteria.andSuidEqualTo(uid);
                List<Friends> friends = friendsMapper.selectByExample(friendsExample);
                List<Integer> uids = new ArrayList<>();
                for (Friends f : friends) {
                    uids.add(f.getDuid());
                }
                /*获取好友ID*/
                commentExampleCriteria.andUidIn(uids);
                break;
            default:
                break;
        }


        commentExample.setOffset(pageResult.getOffset());
        commentExample.setLimit(pageResult.getLimit());

        List<TopicComment> topicComments;

        int count = commentMapper.countByExample(commentExample);
        pageResult.setCount(count);

        if (authenticated) {
            User u = (User) subject.getPrincipal();
            Integer uid = u.getUid();
            topicComments = commentMapper.selectMain(uid, commentExample);
        } else {
            topicComments = commentMapper.selectMain(null, commentExample);
        }

        pageResult.setList(topicComments);
        return pageResult;
    }

    @Override
    public List<TopicMap> getHotTopic() {
        TopicExample topicExample = new TopicExample();
        topicExample.setOrderByClause("coefficient");
        topicExample.setLimit(4);
        List<Topic> topics = topicMapper.selectByExample(topicExample);
        List<TopicMap> topicMaps = new ArrayList<>();
        for (Topic t : topics) {
            topicMaps.add(new TopicMap(t));
        }
        return topicMaps;
    }

    @Override
    public String addTopic(String topic) {
        topic = topic.trim();
        int length = topic.length();
        if (length < 2 ) {
            return StaticEnum.WORD_LENGTH_SHORT;
        }
        if (length > 8) {
            return StaticEnum.WORD_LENGTH_LONG;
        }

        Topic t = new Topic();
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            return StaticEnum.OPT_UNLOGIN;
        }
        User u = (User) subject.getPrincipal();
        t.setUid(u.getUid());
        t.setTopic(topic);
        try {
            int insert = topicMapper.insertSelective(t);
            if (insert != 0) {
                return StaticEnum.OPT_SUCCESS;
            } else {
                return "创建失败";
            }
        } catch (DuplicateKeyException exception) {
            return "话题已存在";
        }
    }
}
