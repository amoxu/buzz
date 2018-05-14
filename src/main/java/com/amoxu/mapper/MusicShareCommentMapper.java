package com.amoxu.mapper;

import com.amoxu.entity.MusicShareComment;
import com.amoxu.entity.MusicShareCommentExample;
import com.amoxu.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MusicShareCommentMapper {
    int countByExample(MusicShareCommentExample example);

    int deleteByExample(MusicShareCommentExample example);

    int deleteByPrimaryKey(Integer cid);

    int insert(MusicShareComment record);

    int insertSelective(MusicShareComment record);

    List<MusicShareComment> selectByExample(MusicShareCommentExample example);

    MusicShareComment selectByPrimaryKey(Integer cid);

    int updateByExampleSelective(@Param("record") MusicShareComment record, @Param("example") MusicShareCommentExample example);

    int updateByExample(@Param("record") MusicShareComment record, @Param("example") MusicShareCommentExample example);

    int updateByPrimaryKeySelective(MusicShareComment record);

    int updateByPrimaryKey(MusicShareComment record);

    List<MusicShareComment> selectMainChild(@Param("onlineId") Integer onlineId, @Param("baseId") Integer baseId );

    List<MusicShareComment> selectChild(@Param("onlineId") Integer onlineId,@Param("baseId") Integer baseId, @Param("example") MusicShareCommentExample example);

    User getReceive(@Param("rcid")Integer rcid,@Param("baseId")Integer baseId);
}