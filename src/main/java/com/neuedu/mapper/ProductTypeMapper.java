package com.neuedu.mapper;

import com.neuedu.pojo.ProductType;
import com.neuedu.pojo.ProductTypeExample;
import com.neuedu.utils.FactoryResult;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductTypeMapper {
    int countByExample(ProductTypeExample example);

    int deleteByExample(ProductTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductType record);

    int insertSelective(ProductType record);

    List<ProductType> selectByExample(ProductTypeExample example);

    ProductType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProductType record, @Param("example") ProductTypeExample example);

    int updateByExample(@Param("record") ProductType record, @Param("example") ProductTypeExample example);

    int updateByPrimaryKey(ProductType record);
    
    //获取通过id数组获取所有的数据
	List<ProductType> findAllProTypeByIds(@Param("ids") int[] ids);
	//获取所有分类及其子类
	List<ProductType> findProTypeAndChildren();
	//根据id获取所有子类
	List<ProductType> findChildrenById(Integer id);
	//获取一级分类
	List<ProductType> findOneClassProType();

	void addProTypeList(@Param("list") List<ProductType> list);

	void updateProType(ProductType productType);
	
	int updateByPrimaryKeySelective(ProductType record);
	
	 
}