package com.amoxu.mapper;

import com.amoxu.entity.Friends;
import com.amoxu.entity.FriendsExample;
import com.amoxu.entity.FriendsKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FriendsMapper {
    int countByExample(FriendsExample example);

    int deleteByExample(FriendsExample example);

    int deleteByPrimaryKey(FriendsKey key);

    int insert(Friends record);

    int insertSelective(Friends record);

    List<Friends> selectByExample(FriendsExample example);

    Friends selectByPrimaryKey(FriendsKey key);

    int updateByExampleSelective(@Param("record") Friends record, @Param("example") FriendsExample example);

    int updateByExample(@Param("record") Friends record, @Param("example") FriendsExample example);

    int updateByPrimaryKeySelective(Friends record);

    int updateByPrimaryKey(Friends record);

    List<Friends> selectSelective(FriendsExample example);
}