package com.amoxu.mapper;

import com.amoxu.entity.Music;
import com.amoxu.entity.MusicExample;
import com.amoxu.entity.MusicInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MusicMapper {
    int countByExample(MusicExample example);

    int deleteByExample(MusicExample example);

    int deleteByPrimaryKey(Integer mid);

    int insert(Music record);

    int insertSelective(Music record);

    List<Music> selectByExample(MusicExample example);

    Music selectByPrimaryKey(Integer mid);

    int updateByExampleSelective(@Param("record") Music record, @Param("example") MusicExample example);

    int updateByExample(@Param("record") Music record, @Param("example") MusicExample example);

    int updateByPrimaryKeySelective(Music record);

    int updateByPrimaryKey(Music record);

    MusicInfo selectMusicInfo(@Param("singer") String artist, @Param("song") String music);
}