package com.neuedu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.neuedu.pojo.Product;
import com.neuedu.pojo.ProductExample;


public interface ProductMapper {
    int counByExample(ProductExample example);

    int deleeByExample(ProductExample example);

    int deleeByPrimaryKey(Integer id);

    int inser(Product record);

    int inserSelecive(Product record);

    List<Product> selecByExample(ProductExample example);

    Product selecByPrimaryKey(Integer id);

    int updaeByExampleSelecive(@Param("record") Product record, @Param("example") ProductExample example);

    int updaeByExample(@Param("record") Product record, @Param("example") ProductExample example);

    int updaeByPrimaryKeySelecive(Product record);

    int updaeByPrimaryKey(Product record);
}