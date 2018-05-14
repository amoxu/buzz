package com.amoxu.mapper;

import com.amoxu.entity.BuzzNetease;
import com.amoxu.entity.BuzzNeteaseExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BuzzNeteaseMapper {
    int countByExample(BuzzNeteaseExample example);

    int deleteByExample(BuzzNeteaseExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BuzzNetease record);

    int insertSelective(BuzzNetease record);

    List<BuzzNetease> selectByExample(BuzzNeteaseExample example);

    BuzzNetease selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BuzzNetease record, @Param("example") BuzzNeteaseExample example);

    int updateByExample(@Param("record") BuzzNetease record, @Param("example") BuzzNeteaseExample example);

    int updateByPrimaryKeySelective(BuzzNetease record);

    int updateByPrimaryKey(BuzzNetease record);

    List<BuzzNetease> selectMain(@Param("onlineId") Integer uid, @Param("example") BuzzNeteaseExample buzzExample);

    List<BuzzNetease> selectTopReply(@Param("onlineId") Integer uid, @Param("example") BuzzNeteaseExample buzzExample);

}