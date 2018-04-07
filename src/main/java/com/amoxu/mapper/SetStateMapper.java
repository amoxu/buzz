package com.amoxu.mapper;

import com.amoxu.entity.SetState;
import com.amoxu.entity.SetStateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SetStateMapper {
    int countByExample(SetStateExample example);

    int deleteByExample(SetStateExample example);

    int deleteByPrimaryKey(Integer ssid);

    int insert(SetState record);

    int insertSelective(SetState record);

    List<SetState> selectByExample(SetStateExample example);

    SetState selectByPrimaryKey(Integer ssid);

    int updateByExampleSelective(@Param("record") SetState record, @Param("example") SetStateExample example);

    int updateByExample(@Param("record") SetState record, @Param("example") SetStateExample example);

    int updateByPrimaryKeySelective(SetState record);

    int updateByPrimaryKey(SetState record);
}