package com.amoxu.mapper;

import com.alibaba.fastjson.JSON;
import com.amoxu.entity.Events;
import com.amoxu.entity.EventsExample;
import com.amoxu.entity.PageResult;
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
@ContextConfiguration(locations = {"classpath:spring/applicationContext-service.xml"
        ,"classpath:spring/applicationContext-dao.xml"
        ,"classpath:spring/applicationContext-mail.xml"
        ,"classpath:spring/applicationContext-trsaction.xml"
})
public class EventsMapperTest {
    private Logger logger = Logger.getLogger(getClass());
    @Autowired
    EventsMapper eventsMapper;
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Test
    public void selectEventsByExample() {
        PageResult<Events> pageResult = new PageResult<>();
        pageResult.setLimit(20);
        pageResult.setOffset(0);
        EventsExample eventsExample = new EventsExample();
        EventsExample.Criteria criteria = eventsExample.createCriteria();
        criteria.andRcidEqualTo(11);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EventsMapper mapper = sqlSession.getMapper(EventsMapper.class);

        pageResult.setCount(mapper.countByExample(eventsExample));
        eventsExample.setOffset(pageResult.getOffset());
        eventsExample.setLimit(pageResult.getLimit());
        List<Events> eventsList = eventsMapper.selectEventsByExample(1,eventsExample);
        logger.info(eventsList);
        logger.info(JSON.toJSONString(eventsList));

    }
}