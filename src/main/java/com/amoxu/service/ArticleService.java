package com.amoxu.service;

import com.amoxu.entity.AjaxResult;
import com.amoxu.entity.Article;

import java.util.List;

public interface ArticleService {

    Article getArticle(Integer id);

    AjaxResult<List<Article>> getNotice();
}
