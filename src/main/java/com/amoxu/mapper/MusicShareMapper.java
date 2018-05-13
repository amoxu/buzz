package com.amoxu.mapper;

import com.amoxu.entity.MusicShare;
import com.amoxu.entity.MusicShareExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MusicShareMapper {
    int countByExample(MusicShareExample example);

    int deleteByExample(MusicShareExample example);

    int deleteByPrimaryKey(Integer oid);

    int insert(MusicShare record);

    int insertSelective(MusicShare record);

    List<MusicShare> selectByExample(MusicShareExample example);

    MusicShare selectByPrimaryKey(Integer oid);

    int updateByExampleSelective(@Param("record") MusicShare record, @Param("example") MusicShareExample example);

    int updateByExample(@Param("record") MusicShare record, @Param("example") MusicShareExample example);

    int updateByPrimaryKeySelective(MusicShare record);

    int updateByPrimaryKey(MusicShare record);

    List<MusicShare> selectMain(@Param("onlineId") Integer onlineId, @Param("example") MusicShareExample example);



}