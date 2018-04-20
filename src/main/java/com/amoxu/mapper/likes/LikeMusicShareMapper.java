package com.amoxu.mapper.likes;

import com.amoxu.entity.ProcCallResult;
import com.amoxu.entity.likes.LikeMusicShare;
import com.amoxu.entity.likes.LikeMusicShareExample;
import com.amoxu.entity.likes.LikeMusicShareKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LikeMusicShareMapper {
    int countByExample(LikeMusicShareExample example);

    int deleteByExample(LikeMusicShareExample example);

    int deleteByPrimaryKey(LikeMusicShareKey key);

    int insert(LikeMusicShare record);

    int insertSelective(LikeMusicShare record);

    List<LikeMusicShare> selectByExample(LikeMusicShareExample example);

    LikeMusicShare selectByPrimaryKey(LikeMusicShareKey key);

    int updateByExampleSelective(@Param("record") LikeMusicShare record, @Param("example") LikeMusicShareExample example);

    int updateByExample(@Param("record") LikeMusicShare record, @Param("example") LikeMusicShareExample example);

    int updateByPrimaryKeySelective(LikeMusicShare record);

    int updateByPrimaryKey(LikeMusicShare record);

    ProcCallResult callLikeProc(ProcCallResult callResult);
}