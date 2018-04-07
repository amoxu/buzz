package com.amoxu.mapper;

import com.amoxu.entity.TopicTalk;
import com.amoxu.entity.TopicTalkExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TopicTalkMapper {
    int countByExample(TopicTalkExample example);

    int deleteByExample(TopicTalkExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TopicTalk record);

    int insertSelective(TopicTalk record);

    List<TopicTalk> selectByExample(TopicTalkExample example);

    TopicTalk selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TopicTalk record, @Param("example") TopicTalkExample example);

    int updateByExample(@Param("record") TopicTalk record, @Param("example") TopicTalkExample example);

    int updateByPrimaryKeySelective(TopicTalk record);

    int updateByPrimaryKey(TopicTalk record);
}