package com.amoxu.mapper;

import com.amoxu.entity.Comments;
import com.amoxu.entity.CommentsExample;
import com.amoxu.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentsMapper {
    int countByExample(CommentsExample example);

    int deleteByExample(CommentsExample example);

    int deleteByPrimaryKey(Integer cid);

    int insert(Comments record);

    int insertSelective(Comments record);

    List<Comments> selectByExample(CommentsExample example);

    Comments selectByPrimaryKey(Integer cid);

    int updateByExampleSelective(@Param("record") Comments record, @Param("example") CommentsExample example);

    int updateByExample(@Param("record") Comments record, @Param("example") CommentsExample example);

    int updateByPrimaryKeySelective(Comments record);

    int updateByPrimaryKey(Comments record);


    List<Comments> selectMainChild(@Param("onlineId") Integer onlineId, @Param("baseId") Integer baseId );

    List<Comments> selectChild(@Param("onlineId") Integer onlineId,@Param("baseId") Integer baseId, @Param("example") CommentsExample example);

    User getReceive(@Param("rcid")Integer rcid, @Param("buzzId")Integer buzzId);


}