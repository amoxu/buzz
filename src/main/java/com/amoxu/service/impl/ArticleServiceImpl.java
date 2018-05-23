package com.amoxu.service.impl;

import com.amoxu.entity.AjaxResult;
import com.amoxu.entity.Article;
import com.amoxu.entity.ArticleExample;
import com.amoxu.mapper.ArticleMapper;
import com.amoxu.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Override
    public Article getArticle(Integer id) {
        Article article = articleMapper.selectByPrimaryKey(id);
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
}
