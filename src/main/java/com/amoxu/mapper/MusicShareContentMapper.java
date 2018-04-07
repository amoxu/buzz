package com.amoxu.mapper;

import com.amoxu.entity.MusicShareContent;
import com.amoxu.entity.MusicShareContentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MusicShareContentMapper {
    int countByExample(MusicShareContentExample example);

    int deleteByExample(MusicShareContentExample example);

    int deleteByPrimaryKey(Integer cid);

    int insert(MusicShareContent record);

    int insertSelective(MusicShareContent record);

    List<MusicShareContent> selectByExample(MusicShareContentExample example);

    MusicShareContent selectByPrimaryKey(Integer cid);

    int updateByExampleSelective(@Param("record") MusicShareContent record, @Param("example") MusicShareContentExample example);

    int updateByExample(@Param("record") MusicShareContent record, @Param("example") MusicShareContentExample example);

    int updateByPrimaryKeySelective(MusicShareContent record);

    int updateByPrimaryKey(MusicShareContent record);
}