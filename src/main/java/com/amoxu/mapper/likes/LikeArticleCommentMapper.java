package com.amoxu.mapper.likes;

import com.amoxu.entity.ProcCallResult;
import com.amoxu.entity.likes.LikeArticleComment;
import com.amoxu.entity.likes.LikeArticleCommentExample;
import com.amoxu.entity.likes.LikeArticleCommentKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LikeArticleCommentMapper {
    int countByExample(LikeArticleCommentExample example);

    int deleteByExample(LikeArticleCommentExample example);

    int deleteByPrimaryKey(LikeArticleCommentKey key);

    int insert(LikeArticleComment record);

    int insertSelective(LikeArticleComment record);

    List<LikeArticleComment> selectByExample(LikeArticleCommentExample example);

    LikeArticleComment selectByPrimaryKey(LikeArticleCommentKey key);

    int updateByExampleSelective(@Param("record") LikeArticleComment record, @Param("example") LikeArticleCommentExample example);

    int updateByExample(@Param("record") LikeArticleComment record, @Param("example") LikeArticleCommentExample example);

    int updateByPrimaryKeySelective(LikeArticleComment record);

    int updateByPrimaryKey(LikeArticleComment record);
    ProcCallResult callLikeProc(ProcCallResult callResult);

}