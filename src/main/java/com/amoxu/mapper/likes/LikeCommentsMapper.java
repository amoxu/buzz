package com.amoxu.mapper.likes;

import com.amoxu.entity.likes.LikeComments;
import com.amoxu.entity.likes.LikeCommentsExample;
import com.amoxu.entity.likes.LikeCommentsKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LikeCommentsMapper {
    int countByExample(LikeCommentsExample example);

    int deleteByExample(LikeCommentsExample example);

    int deleteByPrimaryKey(LikeCommentsKey key);

    int insert(LikeComments record);

    int insertSelective(LikeComments record);

    List<LikeComments> selectByExample(LikeCommentsExample example);

    LikeComments selectByPrimaryKey(LikeCommentsKey key);

    int updateByExampleSelective(@Param("record") LikeComments record, @Param("example") LikeCommentsExample example);

    int updateByExample(@Param("record") LikeComments record, @Param("example") LikeCommentsExample example);

    int updateByPrimaryKeySelective(LikeComments record);

    int updateByPrimaryKey(LikeComments record);
}