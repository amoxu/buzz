package com.amoxu.service.impl;

import com.amoxu.entity.*;
import com.amoxu.exception.UnLoginException;
import com.amoxu.mapper.EventMapper;
import com.amoxu.mapper.FriendsMapper;
import com.amoxu.service.EventService;
import com.amoxu.util.StaticEnum;
import com.amoxu.util.ToolKit;
import com.amoxu.util.WriteLogUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    @Autowired
    private EventMapper eventMapper;
    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    @Autowired
    private WriteLogUtil writeLogUtil;
    private Logger logger = Logger.getLogger(getClass());
    @Autowired
    private FriendsMapper friendsMapper;


    @Override
    public PageResult<Event> getMain(String type, PageResult<Event> pageResult) throws UnLoginException {
        EventExample commentExample = new EventExample();
        EventExample.Criteria commentExampleCriteria = commentExample.createCriteria();

        commentExampleCriteria.andBidEqualTo(0);
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
                    commentExampleCriteria.andBidEqualTo(ttid);
                } catch (Exception e) {
                    logger.info("输入错误的话题ID，" + e.getMessage());
                    pageResult.setCount(0);
                    return pageResult;
                }
                break;
        }


        commentExample.setOffset(pageResult.getOffset());
        commentExample.setLimit(pageResult.getLimit());

        List<Event> events;

        int count = eventMapper.countByExample(commentExample);
        pageResult.setCount(count);
        Integer uid = null;
        if (authenticated) {
            User u = (User) subject.getPrincipal();
            uid = u.getUid();
        }
        events = eventMapper.selectMain(uid, commentExample);


        pageResult.setList(events);
        return pageResult;
    }


    @Override
    public Event publishComment(String data) throws UnLoginException {
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            throw new UnLoginException();
        }
        String s = ToolKit.aesDecrypt(data);
        if (StringUtils.isBlank(s) || s.length() < 2 || s.length() > 512) {
            return null;
        }
        Event event = new Event();
        Integer uid = ((User) subject.getPrincipal()).getUid();
        event.setBid(0);
        event.setContent(s);
        event.setRcid(0);
        event.setUid(uid);
        event.setLikes(0);
        event.setFeeling(1.3);
        eventMapper.insertSelective(event);
        event = eventMapper.selectByPrimaryKey(event.getCid());
        if (event != null) {
            writeLogUtil.writeLog(uid,
                    "/event/comment.html?id=" + event.getCid(),
                    "发表了动态：" + event.getContent());
        }
        /*构造返回值参数*/



        return event;

    }

    /*回复消息 子列表 和首列表*/
    @Override
    public AjaxResult<Event> replyComment(Integer rcid, Integer bcid, String data) {
        AjaxResult<Event> result = new AjaxResult<>();
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
        Integer uid = ((User) subject.getPrincipal()).getUid();

        data = ToolKit.aesDecrypt(data);
        Event comment = new Event();
        comment.setRcid(rcid);
        comment.setBid(bcid);
        comment.setUid(uid);
        comment.setContent(data);

        int i = eventMapper.insertSelective(comment);

        if (i > 0) {
            comment = eventMapper.selectByPrimaryKey(comment.getCid());
            if (comment != null ) {
                result.ok();
                result.setData(comment);
                writeLogUtil.writeLog(uid,
                        "/event/comment.html?id=" + bcid,
                        "回复动态内容：" + data);
            } else {
                result.failed();
            }
        } else {
            result.failed();
        }


        return result;
    }


    /**
     * 分页获取子回复列表
     */
    @Override
    public AjaxResult commentDetail(Integer cid, PageResult<Event> pageResult) {
        AjaxResult<List<Event>> ajaxResult = new AjaxResult<>();
        logger.info(pageResult);

        EventExample example = new EventExample();
        EventExample.Criteria criteria = example.createCriteria();
        criteria.andBidEqualTo(cid);

        int count = eventMapper.countByExample(example);//获取分页总数
        int limit = pageResult.getLimit();
        example.setLimit(limit == 0 ? 10 : limit);
        example.setOffset(pageResult.getOffset());
        example.setOrderByClause("event.ctime desc");
        List<Event> eventsses;

        /*判断用户登录，用于获取点赞信息*/
        Subject subject = SecurityUtils.getSubject();
        Integer uid = null;
        boolean authenticated = subject.isAuthenticated();
        if (authenticated) {
            User u = (User) subject.getPrincipal();
            uid = u.getUid();
        }
        eventsses = eventMapper.selectChild(uid, example);

        ajaxResult.setData(eventsses);
        ajaxResult.setCount(count);

        ajaxResult.ok();
        return ajaxResult;
    }

    /**
     * 获取主评+热评
     * 单条
     * 使用在详细页面
     */
    @Override
    public AjaxResult<List<Event>> getDetailMain(Integer... cid) {
        EventExample commentExample = new EventExample();
        EventExample.Criteria commentExampleCriteria = commentExample.createCriteria();
        commentExample.setLimit(cid.length);
        commentExample.setOffset(0);

        commentExampleCriteria.andBidEqualTo(0);

        commentExampleCriteria.andCidIn(Arrays.asList(cid));

        Subject subject = SecurityUtils.getSubject();
        boolean authenticated = subject.isAuthenticated();

        List<Event> events;
        Integer uid = null;

        if (authenticated) {
            User u = (User) subject.getPrincipal();
            uid = u.getUid();
        }
        events = eventMapper.selectMain(uid, commentExample);
        /*
         * 构造返回数据
         *
         * */
        AjaxResult<List<Event>> ajaxResult = new AjaxResult<>();
        ajaxResult.ok();
        ajaxResult.setData(events);
        ajaxResult.setCount(events.size());

        return ajaxResult;

    }
}
