package com.amoxu.service.impl;

import com.amoxu.entity.*;
import com.amoxu.exception.UnLoginException;
import com.amoxu.mapper.FriendsMapper;
import com.amoxu.mapper.PermissionMapper;
import com.amoxu.service.FriendsService;
import com.amoxu.util.StaticEnum;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendsServiceImpl implements FriendsService {
    private Logger logger = Logger.getLogger(getClass());
    @Autowired
    private FriendsMapper friendsMapper;

    @Autowired
    private PermissionMapper permissionMapper;

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
    public AjaxResult<List<Friends>> getFriends(Integer uid, PageResult<Friends> pageResult) {

        AjaxResult<List<Friends>> ajaxResult = new AjaxResult<>();

        Subject subject = SecurityUtils.getSubject();
        if (uid == null && subject.isAuthenticated()) {
            uid = ((User) subject.getPrincipal()).getUid();
        } else if (uid == null && !subject.isAuthenticated()) {
            ajaxResult.failed();
            ajaxResult.setMsg(StaticEnum.OPT_UNLOGIN);
            return ajaxResult;
        }

        Permission permission = permissionMapper.selectByPrimaryKey(uid);
        if (permission == null) {
            ajaxResult.failed();
            ajaxResult.setMsg(StaticEnum.USER_NOT_EXIST);
            return ajaxResult;
        }
        Integer messageRole = permission.getFriend();

        if (!subject.isAuthenticated() && messageRole > 0) {
            ajaxResult.failed();
            ajaxResult.setMsg(StaticEnum.PERMISSION_DENIED);
            return ajaxResult;
        } else if (messageRole > 0) {
            Integer viewId = ((User) subject.getPrincipal()).getUid();

            if (messageRole == 2 && viewId != uid) {
                //仅自己可见
                ajaxResult.failed();
                ajaxResult.setMsg(StaticEnum.PERMISSION_DENIED);
                return ajaxResult;
            }
            if (messageRole == 1 && viewId != uid) {
                //朋友可见
                FriendsExample friendsExample = new FriendsExample();
                friendsExample.or().andSuidEqualTo(uid).andDuidEqualTo(viewId);
                List<Friends> friends = friendsMapper.selectByExample(friendsExample);
                if (friends != null && friends.size() > 0) {
                    /*
                     * 不用friends == null || friends.size() <= 0
                     * 避免空指针异常
                     * */
                } else {
                    ajaxResult.failed();
                    ajaxResult.setMsg(StaticEnum.PERMISSION_DENIED);
                    return ajaxResult;
                }
            }
        }


        /*int uid = 1;*/
        logger.info(pageResult);

        ajaxResult.ok();

        FriendsExample example = new FriendsExample();
        FriendsExample.Criteria criteria = example.createCriteria();
        criteria.andSuidEqualTo(uid);
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

        ajaxResult.setData(pageResult.getList());

        ajaxResult.setCount(pageResult.getCount());
        return ajaxResult;

    }

    @Override
    public AjaxResult addFriend(Integer uid) throws UnLoginException {
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            throw new UnLoginException();
        }
        int suid = ((User) subject.getPrincipal()).getUid();
        Friends friends = new Friends();
        friends.setSuid(suid);
        friends.setDuid(uid);
        int i = friendsMapper.insertSelective(friends);
        AjaxResult<String> ajaxResult = new AjaxResult<>();
        if (i > 0) {
            ajaxResult.ok();
        } else {
            ajaxResult.failed();
        }
        return ajaxResult;
    }
}
