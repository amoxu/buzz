package com.amoxu.mapper.likes;

import com.amoxu.entity.ProcCallResult;
import com.amoxu.entity.likes.LikeTopicComment;
import com.amoxu.entity.likes.LikeTopicCommentExample;
import com.amoxu.entity.likes.LikeTopicCommentKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LikeTopicCommentMapper {
    int countByExample(LikeTopicCommentExample example);

    int deleteByExample(LikeTopicCommentExample example);

    int deleteByPrimaryKey(LikeTopicCommentKey key);

    int insert(LikeTopicComment record);

    int insertSelective(LikeTopicComment record);

    List<LikeTopicComment> selectByExample(LikeTopicCommentExample example);

    LikeTopicComment selectByPrimaryKey(LikeTopicCommentKey key);

    int updateByExampleSelective(@Param("record") LikeTopicComment record, @Param("example") LikeTopicCommentExample example);

    int updateByExample(@Param("record") LikeTopicComment record, @Param("example") LikeTopicCommentExample example);

    int updateByPrimaryKeySelective(LikeTopicComment record);

    int updateByPrimaryKey(LikeTopicComment record);

    /*调用存储过程*/
    ProcCallResult callLikeProc(ProcCallResult callResult);

}