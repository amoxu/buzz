package com.amoxu.mapper;

import com.amoxu.entity.CarouselType;
import com.amoxu.entity.CarouselTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CarouselTypeMapper {
    int countByExample(CarouselTypeExample example);

    int deleteByExample(CarouselTypeExample example);

    int deleteByPrimaryKey(Integer tid);

    int insert(CarouselType record);

    int insertSelective(CarouselType record);

    List<CarouselType> selectByExample(CarouselTypeExample example);

    CarouselType selectByPrimaryKey(Integer tid);

    int updateByExampleSelective(@Param("record") CarouselType record, @Param("example") CarouselTypeExample example);

    int updateByExample(@Param("record") CarouselType record, @Param("example") CarouselTypeExample example);

    int updateByPrimaryKeySelective(CarouselType record);

    int updateByPrimaryKey(CarouselType record);
}