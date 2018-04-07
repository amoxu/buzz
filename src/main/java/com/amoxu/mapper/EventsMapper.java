package com.amoxu.mapper;

import com.amoxu.entity.Events;
import com.amoxu.entity.EventsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EventsMapper {
    int countByExample(EventsExample example);

    int deleteByExample(EventsExample example);

    int deleteByPrimaryKey(Integer cid);

    int insert(Events record);

    int insertSelective(Events record);

    List<Events> selectByExample(EventsExample example);

    Events selectByPrimaryKey(Integer cid);

    int updateByExampleSelective(@Param("record") Events record, @Param("example") EventsExample example);

    int updateByExample(@Param("record") Events record, @Param("example") EventsExample example);

    int updateByPrimaryKeySelective(Events record);

    int updateByPrimaryKey(Events record);
}