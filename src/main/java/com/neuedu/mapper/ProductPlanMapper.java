package com.neuedu.mapper;

import com.neuedu.pojo.ProductPlan;
import com.neuedu.pojo.ProductPlanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductPlanMapper {
    int countByExample(ProductPlanExample example);

    int deleteByExample(ProductPlanExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductPlan record);

    int insertSelective(ProductPlan record);

    List<ProductPlan> selectByExample(ProductPlanExample example);

    ProductPlan selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProductPlan record, @Param("example") ProductPlanExample example);

    int updateByExample(@Param("record") ProductPlan record, @Param("example") ProductPlanExample example);

    int updateByPrimaryKeySelective(ProductPlan record);

    int updateByPrimaryKey(ProductPlan record);
}