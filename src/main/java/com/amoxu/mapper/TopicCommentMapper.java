package com.amoxu.mapper;

import com.amoxu.entity.TopicComment;
import com.amoxu.entity.TopicCommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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
}