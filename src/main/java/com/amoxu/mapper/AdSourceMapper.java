package com.amoxu.mapper;

import com.amoxu.entity.AdSource;
import com.amoxu.entity.AdSourceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdSourceMapper {
    int countByExample(AdSourceExample example);

    int deleteByExample(AdSourceExample example);

    int deleteByPrimaryKey(Integer sid);

    int insert(AdSource record);

    int insertSelective(AdSource record);

    List<AdSource> selectByExample(AdSourceExample example);

    AdSource selectByPrimaryKey(Integer sid);

    int updateByExampleSelective(@Param("record") AdSource record, @Param("example") AdSourceExample example);

    int updateByExample(@Param("record") AdSource record, @Param("example") AdSourceExample example);

    int updateByPrimaryKeySelective(AdSource record);

    int updateByPrimaryKey(AdSource record);
}