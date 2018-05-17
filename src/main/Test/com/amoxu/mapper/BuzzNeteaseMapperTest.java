package com.amoxu.mapper;

import com.alibaba.fastjson.JSON;
import com.amoxu.entity.BuzzNetease;
import com.amoxu.entity.BuzzNeteaseExample;
import com.amoxu.util.HanlpUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Iterator;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-service.xml"
        , "classpath:spring/applicationContext-dao.xml"
        , "classpath:spring/applicationContext-mail.xml"
        , "classpath:spring/applicationContext-trsaction.xml"
})
public class BuzzNeteaseMapperTest {

    private Logger logger = Logger.getLogger(getClass());
    @Autowired
    BuzzNeteaseMapper mapper;

    @Autowired
    SqlSessionFactory sqlSessionFactory;
    @Test
    public void selectMain() {
        BuzzNeteaseExample buzzNeteaseExample = new BuzzNeteaseExample();
        buzzNeteaseExample.setLimit(10);
        buzzNeteaseExample.setOffset(0);

        List<BuzzNetease> buzzNeteases = mapper.selectTopReply(1, buzzNeteaseExample);
        logger.info(JSON.toJSONString(buzzNeteases));
    }
    @Test
    public void selectMains() {
        mapper.update(1, "1111");
    }

    @Test
    public void addKey() {
        BuzzNeteaseExample buzzNeteaseExample = new BuzzNeteaseExample();

        int offset = 1;

        /**
         * 196700 19:01
         * 336231 22:00
         * 374589 5/16 11:27
         * */
        final int limit = 1000;

        BuzzNetease next;
        Iterator<BuzzNetease> iterator;
        List<BuzzNetease> buzzNeteases;
        String keyword;
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            BuzzNeteaseMapper sqlSessionMapper = sqlSession.getMapper(BuzzNeteaseMapper.class);
            /*提前获取数量*/


            for (; ; ) {
                buzzNeteaseExample.clear();
                buzzNeteaseExample.setLimit(limit);
                buzzNeteaseExample.setOffset(offset);
                offset += limit;

                logger.info("offset is : " + offset);
                buzzNeteases = sqlSessionMapper.select4KeyWord(buzzNeteaseExample);

                if (buzzNeteases == null || buzzNeteases.size() == 0) {
                    continue;
                    //break;
                }
                //logger.info(JSON.toJSONString(buzzNeteases));
                iterator = buzzNeteases.iterator();

                for (; iterator.hasNext(); ) {
                    next = iterator.next();
                    try {
                        keyword = HanlpUtil.getKeyword(next.getContent());
                        sqlSessionMapper.update(next.getId(), keyword);
                    } catch (Throwable e) {
                        System.out.println(e.getMessage());
                    }
                }

            }
        }  finally {
            sqlSession.close();
        }
    }
}