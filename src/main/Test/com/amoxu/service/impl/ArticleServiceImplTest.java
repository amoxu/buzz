package com.amoxu.service.impl;

import com.amoxu.entity.AjaxResult;
import com.amoxu.entity.Article;
import com.amoxu.entity.ArticleExample;
import com.amoxu.mapper.ArticleMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-*.xml"
})

public class ArticleServiceImplTest {

    @Autowired
    private ArticleMapper articleMapper;
    @Test
    public void getArticle() {
        Article article = articleMapper.selectByPrimaryKey(1);
        System.out.println(article.getContent());

    }

    @Test
    public void getNotice() {
        AjaxResult<List<Article>> ajaxResult = new AjaxResult<>();
        ArticleExample example = new ArticleExample();
        example.setLimit(5);
        example.setOffset(0);
        example.setOrderByClause("ctime desc");
        List<Article> articles = articleMapper.selectByExample(example);
        ajaxResult.ok();
        ajaxResult.setData(articles);
        System.out.println(ajaxResult.toString());
    }
}