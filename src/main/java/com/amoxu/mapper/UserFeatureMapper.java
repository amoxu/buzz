package com.amoxu.mapper;

import com.amoxu.entity.UserFeature;
import com.amoxu.entity.UserFeatureExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserFeatureMapper {
    int countByExample(UserFeatureExample example);

    int deleteByExample(UserFeatureExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserFeature record);

    int insertSelective(UserFeature record);

    List<UserFeature> selectByExample(UserFeatureExample example);

    UserFeature selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserFeature record, @Param("example") UserFeatureExample example);

    int updateByExample(@Param("record") UserFeature record, @Param("example") UserFeatureExample example);

    int updateByPrimaryKeySelective(UserFeature record);

    int updateByPrimaryKey(UserFeature record);
}