package com.amoxu.mapper;

import com.amoxu.entity.LikeEvents;
import com.amoxu.entity.LikeEventsExample;
import com.amoxu.entity.LikeEventsKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LikeEventsMapper {
    int countByExample(LikeEventsExample example);

    int deleteByExample(LikeEventsExample example);

    int deleteByPrimaryKey(LikeEventsKey key);

    int insert(LikeEvents record);

    int insertSelective(LikeEvents record);

    List<LikeEvents> selectByExample(LikeEventsExample example);

    LikeEvents selectByPrimaryKey(LikeEventsKey key);

    int updateByExampleSelective(@Param("record") LikeEvents record, @Param("example") LikeEventsExample example);

    int updateByExample(@Param("record") LikeEvents record, @Param("example") LikeEventsExample example);

    int updateByPrimaryKeySelective(LikeEvents record);

    int updateByPrimaryKey(LikeEvents record);
}