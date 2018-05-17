package com.amoxu.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.amoxu.entity.*;
import com.amoxu.exception.UnLoginException;
import com.amoxu.mapper.FriendsMapper;
import com.amoxu.mapper.MusicMapper;
import com.amoxu.mapper.MusicShareCommentMapper;
import com.amoxu.mapper.MusicShareMapper;
import com.amoxu.service.MusicService;
import com.amoxu.util.StaticEnum;
import com.amoxu.util.ToolKit;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Controller
public class MusicServiceImpl implements MusicService {

    @Autowired
    private MusicShareMapper musicShareMapper;
    @Autowired
    private MusicShareCommentMapper shareCommentMapper;
    @Autowired
    private FriendsMapper friendsMapper;
    @Autowired
    private MusicMapper musicMapper;
    private Logger logger = Logger.getLogger(getClass());

    @Override
    public AjaxResult<MusicShare> shareMusic(String data) throws UnLoginException {

        AjaxResult<MusicShare> result = new AjaxResult<>();

        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            throw new UnLoginException();
        }
        String s = ToolKit.aesDecrypt(data);
        JSONObject jsonObject = JSON.parseObject(s);

        String content = jsonObject.getString("content");
        String song = jsonObject.getString("song");
        song = new String(song.getBytes(), Charset.defaultCharset());
        String singer = jsonObject.getString("singer");
        singer = new String(singer.getBytes(), Charset.defaultCharset());

        /*获取歌曲id*/
        MusicInfo musicInfo = musicMapper.selectMusicInfo(singer, song);
        if (musicInfo == null) {
            result.failed().setMsg("歌曲不存在或输入信息不准确");
            return result;
        }


        MusicShare musicShare = new MusicShare();
        musicShare.setUid(((User) subject.getPrincipal()).getUid());
        musicShare.setMid(musicInfo.getMid());
        musicShare.setContent(content);

        musicShareMapper.insertSelective(musicShare);

        result.ok();
        result.setData(musicShareMapper.selectByPrimaryKey(musicShare.getOid()));
        return result;
    }

    @Override

    public PageResult<MusicShare> getMain(String type, PageResult<MusicShare> pageResult) throws UnLoginException {

        MusicShareExample commentExample = new MusicShareExample();
        MusicShareExample.Criteria commentExampleCriteria = commentExample.createCriteria();

        Subject subject = SecurityUtils.getSubject();
        boolean authenticated = subject.isAuthenticated();

        switch (type) {
            case "hot":
                commentExample.setOrderByClause("likes desc");
                break;
            case "new":
                commentExample.setOrderByClause("ctime desc");
                break;
            case "rand":
                commentExample.setOrderByClause("rand()");
                break;
            case "focus":
                if (!authenticated) {
                    throw new UnLoginException();
                }
                /*获取好友ID*/
                User u = (User) subject.getPrincipal();
                Integer uid = u.getUid();
                FriendsExample friendsExample = new FriendsExample();
                FriendsExample.Criteria friendsExampleCriteria = friendsExample.createCriteria();
                friendsExampleCriteria.andSuidEqualTo(uid);
                List<Friends> friends = friendsMapper.selectByExample(friendsExample);
                List<Integer> uids = new ArrayList<>();
                for (Friends f : friends) {
                    uids.add(f.getDuid());
                }
                /*获取好友ID*/
                commentExampleCriteria.andUidIn(uids);
                break;
            default:

                break;
        }


        commentExample.setOffset(pageResult.getOffset());
        commentExample.setLimit(pageResult.getLimit());

        List<MusicShare> topicComments;

        int count = musicShareMapper.countByExample(commentExample);
        pageResult.setCount(count);

        if (authenticated) {
            User u = (User) subject.getPrincipal();
            Integer uid = u.getUid();
            topicComments = musicShareMapper.selectMain(uid, commentExample);
        } else {
            topicComments = musicShareMapper.selectMain(null, commentExample);
        }

        pageResult.setList(topicComments);
        return pageResult;
    }

    @Override
    public AjaxResult replyComment(Integer rcid, Integer bcid, String data) {
        AjaxResult<MusicShareComment> ret = new AjaxResult<>();
        MusicShareComment shareComment = new MusicShareComment();

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


        shareComment.setRcid(rcid);
        shareComment.setBaseCid(bcid);
        shareComment.setContent(data);

        shareCommentMapper.insertSelective(shareComment);
        shareComment = shareCommentMapper.selectByPrimaryKey(shareComment.getCid());

        ret.ok().setData(shareComment);

        return ret;
    }

    @Override
    public AjaxResult getDetailMain(Integer... oid) {
        MusicShareExample shareExample = new MusicShareExample();
        MusicShareExample.Criteria commentExampleCriteria = shareExample.createCriteria();
        shareExample.setLimit(oid.length);
        shareExample.setOffset(0);

        commentExampleCriteria.andOidIn(Arrays.asList(oid));

        Subject subject = SecurityUtils.getSubject();
        boolean authenticated = subject.isAuthenticated();

        List<MusicShare> musicShares;

        if (authenticated) {
            User u = (User) subject.getPrincipal();
            Integer uid = u.getUid();
            musicShares = musicShareMapper.selectMain(uid, shareExample);
        } else {
            musicShares = musicShareMapper.selectMain(null, shareExample);
        }


        /*
         * 构造返回数据
         *
         * */
        AjaxResult<List<MusicShare>> ajaxResult = new AjaxResult<>();
        ajaxResult.ok();
        ajaxResult.setData(musicShares);
        ajaxResult.setCount(musicShares.size());

        return ajaxResult;

    }

    @Override
    public AjaxResult commentDetail(Integer cid, PageResult<TopicComment> pageResult) {
        AjaxResult<List<MusicShareComment>> ajaxResult = new AjaxResult<>();
        logger.info(pageResult);

        MusicShareCommentExample example = new MusicShareCommentExample();
        MusicShareCommentExample.Criteria criteria = example.createCriteria();
        criteria.andBaseCidEqualTo(cid);

        int count = shareCommentMapper.countByExample(example);//获取分页总数
        example.clear();

        example.setLimit(pageResult.getLimit());
        example.setOffset(pageResult.getOffset());
        example.setOrderByClause("ctime desc");
        List<MusicShareComment> topicComments;

        /*判断用户登录，用于获取点赞信息*/
        Subject subject = SecurityUtils.getSubject();
        boolean authenticated = subject.isAuthenticated();
        if (authenticated) {
            User u = (User) subject.getPrincipal();
            Integer uid = u.getUid();
            topicComments = shareCommentMapper.selectChild(uid,cid, example);
        } else {
            topicComments = shareCommentMapper.selectChild(null, cid, example);
        }

        ajaxResult.setData(topicComments);
        ajaxResult.setCount(count);

        ajaxResult.ok();
        return ajaxResult;
    }

    @Override
    public AjaxResult index() {
        int allCount = shareCommentMapper.countByExample(null);
        Random random = new Random();
        AjaxResult detailMain;
        while (true) {
            detailMain = getDetailMain(random.nextInt(allCount), random.nextInt(allCount));
            if (detailMain.getCount() > 0) {
                return detailMain;
            }
        }
    }

}
