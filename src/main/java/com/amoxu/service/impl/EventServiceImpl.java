package com.amoxu.service.impl;

import com.amoxu.entity.Events;
import com.amoxu.entity.EventsExample;
import com.amoxu.entity.PageResult;
import com.amoxu.entity.User;
import com.amoxu.mapper.EventsMapper;
import com.amoxu.service.EventService;
import com.amoxu.util.ToolKit;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    @Autowired
    private EventsMapper eventsMapper;
    @Autowired
    private SqlSessionFactory sqlSessionFactory;




    private Logger logger = Logger.getLogger(getClass());

    @Override
    public Events replyOrPublish(int uid, Integer rcid, String msg) {
        if (rcid == null) {
            rcid = 0;
        }
        Events events = new Events();
        events.setRcid(rcid);
        events.setUid(uid);
        events.setContent(ToolKit.aesDecrypt(msg));
        events.setLikes(0);
        events.setCtime(new Date());
        events.setFeeling(1.3);
        int i = eventsMapper.insertSelective(events);
        /*设置返回信息的头像和id*/
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        events.setUser(new User());
        events.getUser().setNickname(user.getNickname());
        events.getUser().setIcons(user.getIcons());
        logger.info(i);
        logger.info(events);
        if (i > 0) {
            return events;
        } else {
            return null;
        }
    }

    /**
     *
     * 获取回复动态，
     * 前十条点赞最多，后面按时间排序
     *
     *
     * */
    @Override
    public PageResult<Events> getEvents(PageResult<Events> pageResult, Integer commentId) {
        EventsExample eventsExample = new EventsExample();
        EventsExample.Criteria criteria = eventsExample.createCriteria();
        criteria.andRcidEqualTo(commentId);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EventsMapper mapper = sqlSession.getMapper(EventsMapper.class);
        pageResult.setCount(mapper.countByExample(eventsExample));
        eventsExample.setOffset(pageResult.getOffset());
        eventsExample.setLimit(pageResult.getLimit());
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            Integer uid = ((User) subject.getPrincipal()).getUid();
            List<Events> events = mapper.selectEventsByExample(uid, eventsExample);
            pageResult.setList(events);

        } else {
            List<Events> events = mapper.selectEventsByExample(null, eventsExample);
            pageResult.setList(events);
        }
        sqlSession.close();
        return pageResult;
    }

    /**
     *
     *
     * 获取当前最新的动态。
     *
     * */
    @Override
    public PageResult<Events> getEvents(PageResult<Events> pageResult) {
        EventsExample eventsExample = new EventsExample();
        EventsExample.Criteria criteria /*= eventsExample.createCriteria()*/;
        /*criteria.andRcidEqualTo(0);*/
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EventsMapper mapper = sqlSession.getMapper(EventsMapper.class);
        pageResult.setCount(mapper.countByExample(eventsExample));
        eventsExample.setOrderByClause("ctime desc");
        eventsExample.setOffset(pageResult.getOffset());
        eventsExample.setLimit(pageResult.getLimit());
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            Integer uid = ((User) subject.getPrincipal()).getUid();
            List<Events> events = mapper.selectEventsByExample(uid, eventsExample);
            for (int i = 0; i < events.size(); i++) {
                eventsExample.clear();
                criteria = eventsExample.createCriteria();
                criteria.andRcidEqualTo(events.get(i).getCid());
                eventsExample.setOffset(0);
                eventsExample.setLimit(0); /*查询首页子列表时不需要限制数量*/
                events.set(i, events.get(i).setComment(mapper.selectEventsByExample(uid, eventsExample)));
            }
            pageResult.setList(events);

        } else {
            List<Events> events = mapper.selectEventsByExample(null, eventsExample);
            for (int i = 0; i < events.size(); i++) {
                eventsExample.clear();
                criteria = eventsExample.createCriteria();
                criteria.andRcidEqualTo(events.get(i).getCid());
                eventsExample.setOffset(0);
                eventsExample.setLimit(0); /*查询首页子列表时不需要限制数量*/
                events.set(i, events.get(i).setComment(mapper.selectEventsByExample(null, eventsExample)));
                logger.info(events.get(i));

            }
            pageResult.setList(events);
        }
        sqlSession.close();
        return pageResult;
    }
}
