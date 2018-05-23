package com.amoxu.mapper;

import com.amoxu.entity.Event;
import com.amoxu.entity.EventExample;
import com.amoxu.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EventMapper {
    int countByExample(EventExample example);

    int deleteByExample(EventExample example);

    int deleteByPrimaryKey(Integer cid);

    int insert(Event record);

    int insertSelective(Event record);

    List<Event> selectByExample(EventExample example);

    Event selectByPrimaryKey(Integer cid);

    int updateByExampleSelective(@Param("record") Event record, @Param("example") EventExample example);

    int updateByExample(@Param("record") Event record, @Param("example") EventExample example);

    int updateByPrimaryKeySelective(Event record);


    List<Event> selectMain(@Param("onlineId") Integer onLine, @Param("example") EventExample commentExample);

    List<Event> selectChild(@Param("onlineId") Integer onLine, @Param("example") EventExample example);

    List<Event> selectMainChild(@Param("onlineId") Integer onLine, @Param("base") Integer i, @Param("target") Integer rcid);


    int updateByPrimaryKey(Event record);

    User selectBaseUser(int bid);
}