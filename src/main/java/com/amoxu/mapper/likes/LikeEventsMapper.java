package com.amoxu.mapper.likes;

import com.amoxu.entity.ProcCallResult;
import com.amoxu.entity.likes.LikeEvents;
import com.amoxu.entity.likes.LikeEventsExample;
import com.amoxu.entity.likes.LikeEventsKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    ProcCallResult callLikeProc(ProcCallResult param);

}