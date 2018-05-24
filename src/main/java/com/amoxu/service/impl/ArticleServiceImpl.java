package com.amoxu.service.impl;

import com.amoxu.entity.*;
import com.amoxu.exception.UnLoginException;
import com.amoxu.mapper.ArticleCommentMapper;
import com.amoxu.mapper.ArticleMapper;
import com.amoxu.service.ArticleService;
import com.amoxu.util.StaticEnum;
import com.amoxu.util.ToolKit;
import com.amoxu.util.WriteLogUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ArticleCommentMapper commentMapper;
    @Autowired
    private WriteLogUtil writeLogUtil;
    @Override
    public Article getArticle(Integer id) {
        Integer uid = null;
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            uid = ((User) subject.getPrincipal()).getUid();

        }
        Article article = articleMapper.selectByPrimaryKey(id, uid);
        System.err.println(article.getContent());

        return article;
    }

    @Override
    public AjaxResult<List<Article>> getNotice() {
        AjaxResult<List<Article>> ajaxResult = new AjaxResult<>();
        ArticleExample example = new ArticleExample();
        example.setLimit(5);
        example.setOffset(0);
        example.setOrderByClause("ctime desc");
        List<Article> articles = articleMapper.selectByExample(example);
        ajaxResult.ok();
        ajaxResult.setData(articles);
        return ajaxResult;
    }

    @Override
    public AjaxResult<List<ArticleComment>> getReplyList(Integer id, PageResult<ArticleComment> pageResult) {
        AjaxResult<List<ArticleComment>> ajaxResult = new AjaxResult<>();

        ArticleCommentExample example = new ArticleCommentExample();
        example.or().andBaseIdEqualTo(id);
        int count = commentMapper.countByExample(example);//获取分页总数
        example.clear();

        example.setLimit(pageResult.getLimit());
        example.setOffset(pageResult.getOffset());
        example.setOrderByClause("ctime desc");
        List<ArticleComment> articleComments;

        /*判断用户登录，用于获取点赞信息*/

        Subject subject = SecurityUtils.getSubject();
        boolean authenticated = subject.isAuthenticated();
        Integer uid = null;
        if (authenticated) {
            User u = (User) subject.getPrincipal();
             uid = u.getUid();
        }

        articleComments = commentMapper.selectChild(uid, id, example);

        ajaxResult.setData(articleComments);
        ajaxResult.setCount(count);
        ajaxResult.ok();
        return ajaxResult;
    }

    @Override
    public AjaxResult reply(Integer rcid, Integer bcid, String data) throws UnLoginException {
        AjaxResult<ArticleComment> ret = new AjaxResult<>();
        ArticleComment shareComment = new ArticleComment();
        Integer uid;
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
                uid = ((User) subject.getPrincipal()).getUid();
                shareComment.setUid(uid);
            }

            data = ToolKit.aesDecrypt(data);
        } catch (Exception e) {
            return ret.failed().setMsg(StaticEnum.ERROR_PARSE);
        }

        shareComment.setBaseId(bcid);
        shareComment.setRaid(rcid);
        shareComment.setContent(data);
        writeLogUtil.writeLog(uid, "/article/" + bcid, "发表了评论：" + data);
        commentMapper.insertSelective(shareComment);
        ArticleCommentExample example = new ArticleCommentExample();
        example.setLimit(1);
        example.setOffset(0);
        example.or().andIdEqualTo(shareComment.getId());

        List<ArticleComment> comments = commentMapper.selectChild(uid, bcid, example);
        if (comments != null && comments.size() > 0) {
            ret.ok().setData(comments.get(0));
        } else {
            ret.failed();
        }

        return ret;
    }
}
