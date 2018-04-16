package com.amoxu.service.impl;

import com.amoxu.entity.Friends;
import com.amoxu.entity.FriendsExample;
import com.amoxu.entity.FriendsKey;
import com.amoxu.entity.User;
import com.amoxu.mapper.FriendsMapper;
import com.amoxu.service.FriendsService;
import com.amoxu.util.StaticEnum;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendsServiceImpl implements FriendsService {
    private Logger logger = Logger.getLogger(getClass());
    @Autowired
    private FriendsMapper friendsMapper;

    @Override
    public List<User> getFriends(Integer id, Integer limit, Integer offset) {
        FriendsExample example = new FriendsExample();
        FriendsExample.Criteria criteria = example.createCriteria();
        criteria.andSuidEqualTo(id);
        example.setLimit(limit);
        example.setOffset(offset);

        return friendsMapper.selectSelective(id);
    }

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
}
