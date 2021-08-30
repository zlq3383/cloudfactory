package com.neuedu.mapper;

import com.neuedu.pojo.ProductSchedule;
import com.neuedu.pojo.ProductScheduleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductScheduleMapper {
    int countByExample(ProductScheduleExample example);

    int deleteByExample(ProductScheduleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductSchedule record);

    int insertSelective(ProductSchedule record);

    List<ProductSchedule> selectByExample(ProductScheduleExample example);

    ProductSchedule selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProductSchedule record, @Param("example") ProductScheduleExample example);

    int updateByExample(@Param("record") ProductSchedule record, @Param("example") ProductScheduleExample example);

    int updateByPrimaryKeySelective(ProductSchedule record);

    int updateByPrimaryKey(ProductSchedule record);
}