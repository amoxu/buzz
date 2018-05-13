package com.amoxu.service.impl;

import com.amoxu.entity.*;
import com.amoxu.mapper.*;
import com.amoxu.service.SearchService;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
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
    @Autowired
    private MusicShareMapper shareMapper;

    @Autowired
    private TopicMapper topicMapper;

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    Logger logger = Logger.getLogger(getClass());

    @Override
    public PageResult search(Integer type, String key, PageResult pageResult) {
        switch (type) {
            case 222:/*评论*/
                CommentsExample example = new CommentsExample();
                CommentsExample.Criteria criteria = example.createCriteria();
                criteria.andContentLike("%" + key + "%");
                pageResult.setCount(commentsMapper.countByExample(example));

                example.setLimit(pageResult.getLimit());
                example.setOffset(pageResult.getOffset());

                pageResult.setList(commentsMapper.selectByExample(example));
                break;
            case 100:/*歌曲*/
                MusicExample musicExample = new MusicExample();
                MusicExample.Criteria musicCriteria = musicExample.createCriteria();
                musicCriteria.andNameLike("%" + key + "%");
                pageResult.setCount(musicMapper.countByExample(musicExample));
                musicExample.setLimit(pageResult.getLimit());
                musicExample.setOffset(pageResult.getOffset());

                pageResult.setList(musicMapper.selectByExample(musicExample));
                break;
            case 10:/*歌手*/
                SingerExample singerExample = new SingerExample();
                SingerExample.Criteria singerCriteria = singerExample.createCriteria();
                singerCriteria.andNameLike("%" + key + "%");
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
                userCriteria.andNicknameLike("%" + key + "%");
                pageResult.setCount(userMapper.countByExample(userExample));
                userExample.setLimit(pageResult.getLimit());
                userExample.setOffset(pageResult.getOffset());
                List<User> users = userMapper.selectByExample(userExample);
                for (int i = 0; i < users.size(); i++) {
                    users.get(i).setRoles(null).setRid(null).setState(null).setPassword(null).setNote(null)
                            .setEmail(null).setUserState(null).setCtime(null).setBirth(null);
                }
                pageResult.setList(users);

                break;
            case 1001:/*话题*/
                /*获取话题ID*/
                TopicExample topicExample = new TopicExample();
                TopicExample.Criteria topicExampleCriteria = topicExample.createCriteria();
                topicExampleCriteria.andTopicLike("%" + key + "%");
                List<Topic> topics = topicMapper.selectByExample(topicExample);
                if (topics.size() == 0) {
                    pageResult.setList(Collections.EMPTY_LIST);
                    break;
                }
                List<Integer> list = new ArrayList<>();
                for (Topic topic : topics) {
                    list.add(topic.getTid());
                }

                /*==========================================================*/

                TopicCommentExample commentExample = new TopicCommentExample();
                TopicCommentExample.Criteria criteria1 = commentExample.createCriteria();
                criteria1.andTtidIn(list);
                criteria1.andBaseCidEqualTo(0);
                SqlSession sqlSession = sqlSessionFactory.openSession();
                TopicCommentMapper mapper = sqlSession.getMapper(TopicCommentMapper.class);
                pageResult.setCount(mapper.countByExample(commentExample));

                commentExample.setOrderByClause("ctime desc");
                commentExample.setOffset(pageResult.getOffset());
                commentExample.setLimit(pageResult.getLimit());
                List<TopicComment> topicComments;

                /*============================ceshi==================*/

                /*============================ceshi==================*/

                Subject subject = SecurityUtils.getSubject();
                if (subject.isAuthenticated()) {
                    Integer uid = ((User) subject.getPrincipal()).getUid();
                     topicComments = mapper.selectMain(uid, commentExample);
                    pageResult.setList(topicComments);
                } else {
                    topicComments = mapper.selectMain(null, commentExample);
                    pageResult.setList(topicComments);
                }
                sqlSession.close();
                break;
            case 1002:/*搜索分享音乐*/
                MusicExample musicExample1 = new MusicExample();
                MusicExample.Criteria musicCriteria1 = musicExample1.createCriteria();
                musicCriteria1.andNameLike("%" + key + "%");
                List<Music> musics = musicMapper.selectByExample(musicExample1);
                List<Integer> mids = new ArrayList<>();
                for (Music m : musics) {
                    mids.add(m.getMid());
                }
                /*通过mid查询*/
                MusicShareExample shareExample = new MusicShareExample();
                shareExample.setOffset(pageResult.getOffset());
                shareExample.setLimit(pageResult.getLimit());
                /*统计总数*/
                pageResult.setCount(shareMapper.countByExample(shareExample));
                MusicShareExample.Criteria shareExampleCriteria = shareExample.createCriteria();
                shareExampleCriteria.andMidIn(mids);
                List<MusicShare> musicShares;
                Subject subject1 = SecurityUtils.getSubject();
                if (subject1.isAuthenticated()) {
                    Integer uid = ((User) subject1.getPrincipal()).getUid();
                    musicShares = shareMapper.selectMain(uid, shareExample);

                } else {
                    musicShares = shareMapper.selectMain(null, shareExample);
                }
                pageResult.setList(musicShares);
                break;
            default:
                break;
        }

        return pageResult;
    }

}
