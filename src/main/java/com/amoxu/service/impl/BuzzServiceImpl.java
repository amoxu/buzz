package com.amoxu.service.impl;

import com.amoxu.cache.BuzzCacheDao;
import com.amoxu.entity.*;
import com.amoxu.entity.likes.LikeBuzzKey;
import com.amoxu.exception.UnLoginException;
import com.amoxu.mapper.BuzzNeteaseMapper;
import com.amoxu.mapper.CommentsMapper;
import com.amoxu.mapper.likes.LikeBuzzMapper;
import com.amoxu.service.BuzzService;
import com.amoxu.service.UserFeatureService;
import com.amoxu.util.StaticEnum;
import com.amoxu.util.ToolKit;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class BuzzServiceImpl implements BuzzService {

    private Logger logger = Logger.getLogger(getClass());

    @Autowired
    private LikeBuzzMapper likeBuzzMapper;
    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    @Autowired
    private BuzzNeteaseMapper buzzNeteaseMapper;
    @Autowired
    private CommentsMapper commentsMapper;

    @Resource(name = "userFeatureServiceImpl")
    private UserFeatureService userFeatureService;

    @Autowired
    BuzzCacheDao buzzCache;

    /**
     * data-name="discover" 发现 <!--随机x条评论-->
     * data-name="hot" 热评<!--回复最多-->
     * data-name="recommend"推荐<!--根据爱好推荐-->
     * data-name="thumb"点赞<!--点赞最多-->
     **/
    @Override
    public PageResult<BuzzNetease> getMain(String type, PageResult<BuzzNetease> pageResult) throws UnLoginException {

        BuzzNeteaseExample buzzExample = new BuzzNeteaseExample();
        BuzzNeteaseExample.Criteria buzzExampleCriteria = buzzExample.createCriteria();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BuzzNeteaseMapper sqlSessionMapper = sqlSession.getMapper(BuzzNeteaseMapper.class);

        try {


            /*获取总条数*/
            int allCount = buzzCache.getBuzzCount();

            logger.info("\n=====================总信息条数是：" + allCount + "\n=====================");


            Subject subject;
            boolean authenticated;
            Random random;
            ArrayList<Integer> ids;


            buzzExample.setOffset(pageResult.getOffset());
            buzzExample.setLimit(pageResult.getLimit());

            switch (type) {
                case "discover":
                    random = new Random();
                    ids = new ArrayList<>();
                    for (int i = 0; i < 10; i++) {
                        if (allCount < 2) {
                            allCount = 3;
                        }
                        ids.add(random.nextInt(allCount - 2) + 1);
                    }
                    buzzExampleCriteria.andIdIn(ids);

                    break;
                case "hot":
                    List<BuzzNetease> buzzNeteases;
                    subject = SecurityUtils.getSubject();
                    authenticated = subject.isAuthenticated();
                    if (authenticated) {
                        User u = (User) subject.getPrincipal();
                        Integer uid = u.getUid();
                        buzzNeteases = sqlSessionMapper.selectTopReply(uid, buzzExample);
                    } else {
                        buzzNeteases = sqlSessionMapper.selectTopReply(null, buzzExample);
                    }
                    pageResult.setCount(buzzNeteases == null ? 0 : buzzNeteases.size());
                    pageResult.setList(buzzNeteases);
                    return pageResult;
                case "thumb":
                    buzzExample.setOrderByClause("buzz_netease.liked_num desc");
                    break;
                case "recommend":
                    subject = SecurityUtils.getSubject();
                    authenticated = subject.isAuthenticated();
                    if (!authenticated) {
                        throw new UnLoginException();
                    }
                /*
                User u = (User) subject.getPrincipal();
                Integer uid = u.getUid();
                */
                    random = new Random();
                    ids = new ArrayList<>();
                    for (int i = 0; i < 10; i++) {
                        ids.add(random.nextInt(allCount - 2) + 1);
                    }
                    buzzExampleCriteria.andIdIn(ids);
                    break;
                default:
                    break;
            }

            List<BuzzNetease> buzzNeteaseList;
            int count = sqlSessionMapper.countByExample(buzzExample);
            pageResult.setCount(count);

            subject = SecurityUtils.getSubject();
            authenticated = subject.isAuthenticated();
            if (authenticated) {
                User u = (User) subject.getPrincipal();
                Integer uid = u.getUid();
                buzzNeteaseList = sqlSessionMapper.selectMain(uid, buzzExample);
            } else {
                buzzNeteaseList = sqlSessionMapper.selectMain(null, buzzExample);
            }

            pageResult.setList(buzzNeteaseList);
        } finally {
            sqlSession.close();

        }
        return pageResult;

    }

    @Override
    public AjaxResult index() {
        int allCount = buzzCache.getBuzzCount();
        Random random = new Random();
        AjaxResult detailMain;
        while (true) {
            detailMain = getDetailMain(random.nextInt(allCount));
            if (detailMain.getCount() > 0) {
                return detailMain;
            }
        }

    }

    @Override
    public AjaxResult likeBuzz(Integer bid) {
        AjaxResult<String> ajaxResult = new AjaxResult<>();

        Subject subject = SecurityUtils.getSubject();
        boolean authenticated = subject.isAuthenticated();
        if (!authenticated) {
            ajaxResult.failed().setMsg(StaticEnum.OPT_UNLOGIN);
            return ajaxResult;
        }
        User principal = (User) subject.getPrincipal();
        Integer uid = principal.getUid();

        LikeBuzzKey likeBuzzKey = new LikeBuzzKey();
        likeBuzzKey.setBuzzId(bid).setUid(uid);

        /*点赞*/
        likeBuzzMapper.ignoreIntoByPk(likeBuzzKey);

        //用户已经点赞 -> 相关用户爱好+2
        BuzzNetease buzzNetease = buzzNeteaseMapper.selectByPrimaryKey(bid);
        String keyword = buzzNetease.getKeyword();
        String[] split = keyword.split("/");
        userFeatureService.setUserFeature(uid, 1.0, split);
        ajaxResult.ok();
        return ajaxResult;

    }

    @Override
    public AjaxResult dislikeBuzz(Integer bid) {

        return null;
    }

    @Override
    public AjaxResult replyComment(Integer rcid, Integer bcid, String data) {
        AjaxResult<Comments> ret = new AjaxResult<>();
        Comments shareComment = new Comments();

        try {
            if (StringUtils.isBlank(data) || rcid == null || bcid == null) {
                ret.failed();
                ret.setMsg(StaticEnum.EMPTY_WORD);
                return ret;
            }
            Subject subject = SecurityUtils.getSubject();

            if (!subject.isAuthenticated()) {
                return ret.failed().setMsg(StaticEnum.OPT_UNLOGIN);
            } else {
                shareComment.setUid(((User) subject.getPrincipal()).getUid());
            }

            data = ToolKit.aesDecrypt(data);
        } catch (Exception e) {
            return ret.failed().setMsg(StaticEnum.ERROR_PARSE);
        }

        shareComment.setBuzzId(bcid);
        shareComment.setRcid(rcid);
        shareComment.setContent(data);

        commentsMapper.insertSelective(shareComment);
        shareComment = commentsMapper.selectByPrimaryKey(shareComment.getCid());

        ret.ok().setData(shareComment);

        return ret;
    }

    /**
     * 详细页面头部 包括热评一条和四条回复
     *
     * @param oid --> buzzId 热评id
     */
    @Override
    public AjaxResult getDetailMain(Integer... oid) {
        BuzzNeteaseExample shareExample = new BuzzNeteaseExample();
        BuzzNeteaseExample.Criteria commentExampleCriteria = shareExample.createCriteria();
        commentExampleCriteria.andIdIn(Arrays.asList(oid));
        shareExample.setLimit(oid.length);
        shareExample.setOffset(0);


        Subject subject = SecurityUtils.getSubject();
        boolean authenticated = subject.isAuthenticated();

        List<BuzzNetease> buzzNeteases;

        if (authenticated) {
            User u = (User) subject.getPrincipal();
            Integer uid = u.getUid();
            buzzNeteases = buzzNeteaseMapper.selectMain(uid, shareExample);
        } else {
            buzzNeteases = buzzNeteaseMapper.selectMain(null, shareExample);
        }

        /*
         * 构造返回数据
         *
         **/

        AjaxResult<List<BuzzNetease>> ajaxResult = new AjaxResult<>();
        ajaxResult.ok();
        ajaxResult.setData(buzzNeteases);
        ajaxResult.setCount(buzzNeteases.size());

        return ajaxResult;

    }

    /*详细页面获取流加载子列表*/
    @Override
    public AjaxResult commentDetail(Integer cid, PageResult<Comments> pageResult) {
        AjaxResult<List<Comments>> ajaxResult = new AjaxResult<>();
        logger.info(pageResult);

        CommentsExample example = new CommentsExample();
        CommentsExample.Criteria criteria = example.createCriteria();
        criteria.andBuzzIdEqualTo(cid);
        int count = commentsMapper.countByExample(example);//获取分页总数
        example.clear();

        example.setLimit(pageResult.getLimit());
        example.setOffset(pageResult.getOffset());
        example.setOrderByClause("ctime desc");
        List<Comments> topicComments;

        /*判断用户登录，用于获取点赞信息*/

        Subject subject = SecurityUtils.getSubject();
        boolean authenticated = subject.isAuthenticated();
        if (authenticated) {
            User u = (User) subject.getPrincipal();
            Integer uid = u.getUid();
            topicComments = commentsMapper.selectChild(uid, cid, example);
        } else {
            topicComments = commentsMapper.selectChild(null, cid, example);
        }

        ajaxResult.setData(topicComments);
        ajaxResult.setCount(count);

        ajaxResult.ok();
        return ajaxResult;
    }

}
