package com.amoxu.mapper.likes;

import com.amoxu.entity.ProcCallResult;
import com.amoxu.entity.likes.LikeArticle;
import com.amoxu.entity.likes.LikeArticleExample;
import com.amoxu.entity.likes.LikeArticleKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LikeArticleMapper {
    int countByExample(LikeArticleExample example);

    int deleteByExample(LikeArticleExample example);

    int deleteByPrimaryKey(LikeArticleKey key);

    int insert(LikeArticle record);

    int insertSelective(LikeArticle record);

    List<LikeArticle> selectByExample(LikeArticleExample example);

    LikeArticle selectByPrimaryKey(LikeArticleKey key);

    int updateByExampleSelective(@Param("record") LikeArticle record, @Param("example") LikeArticleExample example);

    int updateByExample(@Param("record") LikeArticle record, @Param("example") LikeArticleExample example);

    int updateByPrimaryKeySelective(LikeArticle record);

    int updateByPrimaryKey(LikeArticle record);

    ProcCallResult callLikeProc(ProcCallResult callResult);

}