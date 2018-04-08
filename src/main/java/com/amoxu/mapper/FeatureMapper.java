package com.amoxu.mapper;

import com.amoxu.entity.Feature;
import com.amoxu.entity.FeatureExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FeatureMapper {
    int countByExample(FeatureExample example);

    int deleteByExample(FeatureExample example);

    int deleteByPrimaryKey(Integer fid);

    int insert(Feature record);

    int insertSelective(Feature record);

    List<Feature> selectByExample(FeatureExample example);

    Feature selectByPrimaryKey(Integer fid);

    int updateByExampleSelective(@Param("record") Feature record, @Param("example") FeatureExample example);

    int updateByExample(@Param("record") Feature record, @Param("example") FeatureExample example);

    int updateByPrimaryKeySelective(Feature record);

    int updateByPrimaryKey(Feature record);

    List<Feature> selectByRandom(@Param("limit") Integer limit);
}