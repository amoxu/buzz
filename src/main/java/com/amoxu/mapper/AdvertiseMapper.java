package com.amoxu.mapper;

import com.amoxu.entity.Advertise;
import com.amoxu.entity.AdvertiseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdvertiseMapper {
    int countByExample(AdvertiseExample example);

    int deleteByExample(AdvertiseExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Advertise record);

    int insertSelective(Advertise record);

    List<Advertise> selectByExample(AdvertiseExample example);

    Advertise selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Advertise record, @Param("example") AdvertiseExample example);

    int updateByExample(@Param("record") Advertise record, @Param("example") AdvertiseExample example);

    int updateByPrimaryKeySelective(Advertise record);

    int updateByPrimaryKey(Advertise record);
}