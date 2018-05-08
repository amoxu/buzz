package com.amoxu.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.amoxu.entity.*;
import com.amoxu.exception.UnLoginException;
import com.amoxu.mapper.FriendsMapper;
import com.amoxu.mapper.TopicCommentMapper;
import com.amoxu.mapper.TopicMapper;
import com.amoxu.service.TopicService;
import com.amoxu.util.StaticEnum;
import com.amoxu.util.ToolKit;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {
    @Autowired
    private TopicCommentMapper commentMapper;
    @Autowired
    private TopicMapper topicMapper;

    @Autowired
    private  FriendsMapper friendsMapper;

    private Logger logger = Logger.getLogger(getClass());
    @Override
    public PageResult<TopicComment> getMain(String type, PageResult<TopicComment> pageResult) throws UnLoginException {
        TopicCommentExample commentExample = new TopicCommentExample();
        TopicCommentExample.Criteria commentExampleCriteria = commentExample.createCriteria();

        commentExampleCriteria.andBaseCidEqualTo(0);
        Subject subject = SecurityUtils.getSubject();
        boolean authenticated = subject.isAuthenticated();

        switch (type) {
            case "hot":
                commentExample.setOrderByClause("likes desc");
                break;
            case "new":
                commentExample.setOrderByClause("ctime desc");
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
                /*当输入的内容都不是以上时，进行转换为话题ID*/
                try {
                    int ttid = Integer.parseInt(type);
                    commentExampleCriteria.andTtidEqualTo(ttid);
                } catch (Exception e) {
                    logger.info("输入错误的话题ID，" + e.getMessage());
                    pageResult.setCount(0);
                    return pageResult;
                }
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
        topicExample.setOrderByClause("coefficient desc");
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
        if (length < 2 || length > 32) {
            return StaticEnum.WORD_TOPIC_LENGTH;
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

    @Override
    public TopicComment publishComment(String data) throws UnLoginException {
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            throw new UnLoginException();
        }
        String s = ToolKit.aesDecrypt(data);
        JSONObject jsonObject = JSON.parseObject(s);
        String topicName = jsonObject.getString("topic").trim();


        /*添加到话题表*/
        addTopic(topicName);

        TopicExample topicExample = new TopicExample();
        TopicExample.Criteria topicExampleCriteria = topicExample.createCriteria();
        topicExampleCriteria.andTopicEqualTo(topicName);
        List<Topic> topics = topicMapper.selectByExample(topicExample);
        Integer tid = null;
        for (Topic t : topics) {
            tid = t.getTid();
        }
        if (tid == null) {
            return null;
        }


        TopicComment topicComment = new TopicComment();
        topicComment.setTtid(tid);
        topicComment.setUid(((User) subject.getPrincipal()).getUid());
        topicComment.setBaseCid(0);
        topicComment.setContent(jsonObject.getString("comment"));

        commentMapper.insertSelective(topicComment);
        /*构造返回值参数*/
        topicComment.setSendUser(((User) subject.getPrincipal()).publicUser());
        topicComment.setTopic(topicName);
        topicComment.setComment(Collections.<TopicComment>emptyList());
        topicComment.setCtime(new Date());
        topicComment.setLikes(0);
        /*构造返回值参数*/

        return topicComment;

    }

    @Override
    public AjaxResult<TopicComment> replyComment(Integer rcid, Integer bcid, String data) {
        AjaxResult<TopicComment> result = new AjaxResult<>();
        if (StringUtils.isBlank(data) || rcid == null || bcid == null) {
            result.failed();
            result.setMsg(StaticEnum.EMPTY_WORD);
            return result;
        }


        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            result.failed();
            result.setMsg(StaticEnum.OPT_UNLOGIN);
            return result;
        }
        User u = (User) subject.getPrincipal();

        data = ToolKit.aesDecrypt(data);
        TopicComment comment = new TopicComment();
        comment.setRcid(rcid);
        comment.setBaseCid(bcid);
        comment.setUid(u.getUid());
        comment.setContent(data);
        comment.setTtid(0);/*回复子列表时，话题ID为0*/

        commentMapper.insertSelective(comment);
        comment = commentMapper.selectByPrimaryKey(comment.getCid());

        result.ok();
        result.setData(comment);
        return result;
    }

    /**
     * 分页获取子回复列表
     *
     * */
    @Override
    public AjaxResult commentDetail(Integer cid, PageResult<TopicComment> pageResult) {
        AjaxResult<List<TopicComment>> ajaxResult = new AjaxResult<>();
        logger.info(pageResult);

        TopicCommentExample example = new TopicCommentExample();
        TopicCommentExample.Criteria criteria = example.createCriteria();
        criteria.andBaseCidEqualTo(cid);

        int count = commentMapper.countByExample(example);//获取分页总数

        example.setLimit(pageResult.getLimit());
        example.setOffset(pageResult.getOffset());
        example.setOrderByClause("topic_comment.ctime desc");
        List<TopicComment> topicComments;

        /*判断用户登录，用于获取点赞信息*/
        Subject subject = SecurityUtils.getSubject();
        boolean authenticated = subject.isAuthenticated();
        if (authenticated) {
            User u = (User) subject.getPrincipal();
            Integer uid = u.getUid();
            topicComments = commentMapper.selectChild(uid, example);
        } else {
             topicComments = commentMapper.selectChild(null, example);
        }

        ajaxResult.setData(topicComments);
        ajaxResult.setCount(count);

        ajaxResult.ok();
        return ajaxResult;
    }

    /**
     * 获取主评+热评
     * 单条
     * 使用在详细页面
     * */
    @Override
    public AjaxResult<List<TopicComment>> getDetailMain(Integer cid) {
        TopicCommentExample commentExample = new TopicCommentExample();
        TopicCommentExample.Criteria commentExampleCriteria = commentExample.createCriteria();
        commentExample.setLimit(1);
        commentExample.setOffset(0);

        commentExampleCriteria.andBaseCidEqualTo(0);
        commentExampleCriteria.andCidEqualTo(cid);

        Subject subject = SecurityUtils.getSubject();
        boolean authenticated = subject.isAuthenticated();

        List<TopicComment> topicComments;

        if (authenticated) {
            User u = (User) subject.getPrincipal();
            Integer uid = u.getUid();
            topicComments = commentMapper.selectMain(uid, commentExample);
        } else {
            topicComments = commentMapper.selectMain(null, commentExample);
        }


        /*
         * 构造返回数据
         *
         * */
        AjaxResult<List<TopicComment>> ajaxResult = new AjaxResult<>();
        ajaxResult.ok();
        ajaxResult.setData(topicComments);
        ajaxResult.setCount(topicComments.size());

        return ajaxResult;

    }
}
