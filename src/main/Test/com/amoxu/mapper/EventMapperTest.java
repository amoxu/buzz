package com.amoxu.mapper;

import com.alibaba.fastjson.JSON;
import com.amoxu.entity.Event;
import com.amoxu.entity.EventExample;
import com.amoxu.entity.PageResult;
import com.amoxu.entity.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-*.xml"})
public class EventMapperTest {
    private Logger logger = Logger.getLogger(getClass());
    @Autowired
    EventMapper eventMapper;
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Test
    public void selectEventsByExample() {
        PageResult<Event> pageResult = new PageResult<>();
        pageResult.setLimit(20);
        pageResult.setOffset(0);
        EventExample eventsExample = new EventExample();
       /* EventExample.Criteria criteria = eventsExample.createCriteria();
        criteria.andRcidEqualTo(11);*/
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EventMapper mapper = sqlSession.getMapper(EventMapper.class);

        pageResult.setCount(mapper.countByExample(eventsExample));
        eventsExample.setOffset(pageResult.getOffset());
        eventsExample.setLimit(pageResult.getLimit());

    }


    @Test
    public void selecMain() {
        EventExample eventExample = new EventExample();
        eventExample.setLimit(10);
        eventExample.setOffset(0);
        eventExample.setOrderByClause("ctime desc");
        List<Event> events = eventMapper.selectMain(1, eventExample);
        System.out.println(JSON.toJSONString(events));
    }
    @Test
    public void selectChild() {
        EventExample eventExample = new EventExample();
        eventExample.setLimit(10);
        eventExample.setOffset(0);
        eventExample.setOrderByClause("ctime desc");
        eventExample.or().andBidEqualTo(10255);
        List<Event> events = eventMapper.selectChild(1, eventExample);
        System.out.println(JSON.toJSONString(events));
    }

    @Test
    public void selectPk() {
        Event event = eventMapper.selectByPrimaryKey(10255);
        logger.info(JSON.toJSONString(event));

    }

    @Test
    public void selectUser() {

        User user = eventMapper.selectBaseUser(10255);
        logger.info(user);

    }


}