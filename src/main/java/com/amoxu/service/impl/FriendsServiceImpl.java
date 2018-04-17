package com.amoxu.service.impl;

import com.amoxu.entity.Friends;
import com.amoxu.entity.FriendsExample;
import com.amoxu.entity.FriendsKey;
import com.amoxu.entity.PageResult;
import com.amoxu.mapper.FriendsMapper;
import com.amoxu.service.FriendsService;
import com.amoxu.util.StaticEnum;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendsServiceImpl implements FriendsService {
    private Logger logger = Logger.getLogger(getClass());
    @Autowired
    private FriendsMapper friendsMapper;


    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public String removeRelation(Integer suid, Integer duid) {

        FriendsKey key = new Friends();
        key.setDuid(duid);
        key.setSuid(suid);
        int code = friendsMapper.deleteByPrimaryKey(key);

        if (code != 0) {
            return StaticEnum.OPT_SUCCESS;
        } else {
            return StaticEnum.OPT_SUCCESS;
        }
    }

    @Override
    public PageResult<Friends> getFriends(Integer id, PageResult<Friends> pageResult) {

        FriendsExample example = new FriendsExample();
        FriendsExample.Criteria criteria = example.createCriteria();
        criteria.andSuidEqualTo(id);
        example.setLimit(pageResult.getLimit());
        example.setOffset(pageResult.getOffset());

        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            FriendsMapper sessionMapper = sqlSession.getMapper(FriendsMapper.class);
            /*提前获取数量*/
            pageResult.setCount(sessionMapper.countByExample(example));

            /*然后配置分页*/
            example.setLimit(pageResult.getLimit());
            example.setOffset(pageResult.getOffset());
            pageResult.setList(sessionMapper.selectSelective(example));
        } finally {
            sqlSession.close();
        }

        return pageResult;
    }

}
