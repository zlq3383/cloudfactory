package com.neuedu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.neuedu.pojo.ProductType;
import com.neuedu.pojo.ProductTypeExample;

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
	List<ProductType> findProTypeAndChildrenValid();
	
	//根据id获取所有子类
	List<ProductType> findChildrenById(Integer id);
	List<ProductType> findChildrenByIdValid(Integer id);
	
	//获取一级分类
	List<ProductType> findOneClassProType();
	List<ProductType> findOneClassProTypeValid();
	
	//批量增加产品类别
	void addProTypeList(@Param("list") List<ProductType> list);
	
	//修改产品类别信息
	void updateProType(ProductType productType);
	
	int updateByPrimaryKeySelective(ProductType record);
	
	//批量删除      获取根据产品类别Id,获取产品类别以及子类别下的产品所对应的不能删除订单
	List<ProductType> selectProOrdersByIds(@Param("ids") int[] ids);
	
	//删除           获取根据产品类别Id,获取产品类别以及子类别下的产品所对应的不能删除订单
	ProductType selectProOrdersById(Integer id);
	
	// 根据id获取所有子类以及子类对应的产品所对应的不能删除订单
	List<ProductType> selectChildrenAndProductsById(Integer id);
	
	//删除产品类型
	void deleteProType(Integer id);
	
	//删除产品类型的子类
	void deleteProTypeChildren(Integer id);
	
}