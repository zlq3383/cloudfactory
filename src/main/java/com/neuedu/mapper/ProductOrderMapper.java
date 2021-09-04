package com.neuedu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.neuedu.pojo.ProductOrder;
import com.neuedu.pojo.ProductOrderExample;

public interface ProductOrderMapper {
    int countByExample(ProductOrderExample example);

    int deleteByExample(ProductOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductOrder record);

    int insertSelective(ProductOrder record);

    List<ProductOrder> selectByExample(ProductOrderExample example);

    ProductOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProductOrder record, @Param("example") ProductOrderExample example);

    int updateByExample(@Param("record") ProductOrder record, @Param("example") ProductOrderExample example);

    int updateByPrimaryKeySelective(ProductOrder record);

    int updateByPrimaryKey(ProductOrder record);
    //根据产品id找到对应的不能删除的产品订单
    ProductOrder selectEnDelProOrdersByProId(Integer id);
}