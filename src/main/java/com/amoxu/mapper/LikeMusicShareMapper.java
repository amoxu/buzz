package com.amoxu.mapper;

import com.amoxu.entity.LikeMusicShare;
import com.amoxu.entity.LikeMusicShareExample;
import com.amoxu.entity.LikeMusicShareKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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
}