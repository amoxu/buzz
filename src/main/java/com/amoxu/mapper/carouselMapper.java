package com.amoxu.mapper;

import com.amoxu.entity.carousel;
import com.amoxu.entity.carouselExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface carouselMapper {
    int countByExample(carouselExample example);

    int deleteByExample(carouselExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(carousel record);

    int insertSelective(carousel record);

    List<carousel> selectByExample(carouselExample example);

    carousel selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") carousel record, @Param("example") carouselExample example);

    int updateByExample(@Param("record") carousel record, @Param("example") carouselExample example);

    int updateByPrimaryKeySelective(carousel record);

    int updateByPrimaryKey(carousel record);
}