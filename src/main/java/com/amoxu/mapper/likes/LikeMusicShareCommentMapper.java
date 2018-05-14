package com.amoxu.mapper.likes;

import com.amoxu.entity.ProcCallResult;
import com.amoxu.entity.likes.LikeMusicShareComment;
import com.amoxu.entity.likes.LikeMusicShareCommentExample;
import com.amoxu.entity.likes.LikeMusicShareCommentKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LikeMusicShareCommentMapper {
    int countByExample(LikeMusicShareCommentExample example);

    int deleteByExample(LikeMusicShareCommentExample example);

    int deleteByPrimaryKey(LikeMusicShareCommentKey key);

    int insert(LikeMusicShareComment record);

    int insertSelective(LikeMusicShareComment record);

    List<LikeMusicShareComment> selectByExample(LikeMusicShareCommentExample example);

    LikeMusicShareComment selectByPrimaryKey(LikeMusicShareCommentKey key);

    int updateByExampleSelective(@Param("record") LikeMusicShareComment record, @Param("example") LikeMusicShareCommentExample example);

    int updateByExample(@Param("record") LikeMusicShareComment record, @Param("example") LikeMusicShareCommentExample example);

    int updateByPrimaryKeySelective(LikeMusicShareComment record);

    int updateByPrimaryKey(LikeMusicShareComment record);
    ProcCallResult callLikeProc(ProcCallResult callResult);

}