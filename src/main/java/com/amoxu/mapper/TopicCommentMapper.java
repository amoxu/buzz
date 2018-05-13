package com.amoxu.mapper;

import com.amoxu.entity.TopicComment;
import com.amoxu.entity.TopicCommentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TopicCommentMapper {
    int countByExample(TopicCommentExample example);

    int deleteByExample(TopicCommentExample example);

    int deleteByPrimaryKey(Integer cid);

    int insert(TopicComment record);

    int insertSelective(TopicComment record);

    List<TopicComment> selectByExample(TopicCommentExample example);

    TopicComment selectByPrimaryKey(Integer cid);

    int updateByExampleSelective(@Param("record") TopicComment record, @Param("example") TopicCommentExample example);

    int updateByExample(@Param("record") TopicComment record, @Param("example") TopicCommentExample example);

    int updateByPrimaryKeySelective(TopicComment record);

    int updateByPrimaryKey(TopicComment record);

    List<TopicComment> selectMain(@Param("onlineId") Integer onLine, @Param("example") TopicCommentExample example);

    List<TopicComment> selectMainChild(@Param("onlineId") Integer onLine, @Param("base") Integer i, @Param("target") Integer rcid);

    List<TopicComment> selectChild(@Param("onlineId") Integer onLine, @Param("example") TopicCommentExample example);

}