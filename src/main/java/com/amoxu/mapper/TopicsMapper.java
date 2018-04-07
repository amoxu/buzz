package com.amoxu.mapper;

import com.amoxu.entity.Topics;
import com.amoxu.entity.TopicsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TopicsMapper {
    int countByExample(TopicsExample example);

    int deleteByExample(TopicsExample example);

    int deleteByPrimaryKey(Integer tid);

    int insert(Topics record);

    int insertSelective(Topics record);

    List<Topics> selectByExample(TopicsExample example);

    Topics selectByPrimaryKey(Integer tid);

    int updateByExampleSelective(@Param("record") Topics record, @Param("example") TopicsExample example);

    int updateByExample(@Param("record") Topics record, @Param("example") TopicsExample example);

    int updateByPrimaryKeySelective(Topics record);

    int updateByPrimaryKey(Topics record);
}