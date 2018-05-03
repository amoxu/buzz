package com.amoxu.service.impl;

import com.amoxu.entity.*;
import com.amoxu.mapper.CommentsMapper;
import com.amoxu.mapper.MusicMapper;
import com.amoxu.mapper.SingerMapper;
import com.amoxu.mapper.UserMapper;
import com.amoxu.service.SearchService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private CommentsMapper commentsMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MusicMapper musicMapper;
    @Autowired
    private SingerMapper singerMapper;
    Logger logger = Logger.getLogger(getClass());

    @Override
    public PageResult search(Integer type, String key, PageResult pageResult) {
        switch (type) {
            case 222:/*评论*/
                CommentsExample example = new CommentsExample();
                CommentsExample.Criteria criteria = example.createCriteria();
                criteria.andContentLike("%"+key+"%");
                pageResult.setCount(commentsMapper.countByExample(example));

                example.setLimit(pageResult.getLimit());
                example.setOffset(pageResult.getOffset());

                pageResult.setList(commentsMapper.selectByExample(example));
                break;
            case 100:/*歌曲*/
                MusicExample musicExample = new MusicExample();
                MusicExample.Criteria musicCriteria = musicExample.createCriteria();
                musicCriteria.andNameLike("%"+key+"%");
                pageResult.setCount(musicMapper.countByExample(musicExample));
                musicExample.setLimit(pageResult.getLimit());
                musicExample.setOffset(pageResult.getOffset());

                pageResult.setList(musicMapper.selectByExample(musicExample));
                break;
            case 10:/*歌手*/
                SingerExample singerExample = new SingerExample();
                SingerExample.Criteria singerCriteria = singerExample.createCriteria();
                singerCriteria.andNameLike("%"+key+"%");
                pageResult.setCount(singerMapper.countByExample(singerExample));
                singerExample.setLimit(pageResult.getLimit());
                singerExample.setOffset(pageResult.getOffset());

                pageResult.setList(singerMapper.selectByExample(singerExample));
                break;
            case 1014:/*用户*/
                UserExample userExample = new UserExample();
                UserExample.Criteria userCriteria = userExample.createCriteria();
                try {
                    if (StringUtils.isNumeric(key)) {
                        userCriteria.andUidEqualTo(Integer.valueOf(key));
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();

                    logger.debug("转换失败");
                }
                userCriteria.andNicknameLike("%"+key+"%");
                pageResult.setCount(userMapper.countByExample(userExample));
                userExample.setLimit(pageResult.getLimit());
                userExample.setOffset(pageResult.getOffset());
                List<User> users = userMapper.selectByExample(userExample);
                for (int i =0 ;i<users.size();i++) {
                    users.get(i).setRoles(null).setRid(null).setState(null).setPassword(null).setNote(null)
                            .setEmail(null).setUserState(null).setCtime(null).setBirth(null);
                }
                pageResult.setList(users);

                break;
            default :
                break;
        }

        return pageResult;
    }

}
