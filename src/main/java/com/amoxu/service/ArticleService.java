package com.amoxu.service;

import com.amoxu.entity.AjaxResult;
import com.amoxu.entity.Article;
import com.amoxu.entity.ArticleComment;
import com.amoxu.entity.PageResult;
import com.amoxu.exception.UnLoginException;

import java.util.List;

public interface ArticleService {

    Article getArticle(Integer id);

    AjaxResult<List<Article>> getNotice();

    AjaxResult<List<ArticleComment>> getReplyList(Integer id, PageResult<ArticleComment> pageResult);

    AjaxResult reply(Integer rcid, Integer bcid, String data) throws UnLoginException;
}
