package com.neuedu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.neuedu.pojo.Product;
import com.neuedu.pojo.ProductExample;


public interface ProductMapper {
    int countByExample(ProductExample example);

    int deleteByExample(ProductExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    List<Product> selectByExample(ProductExample example);

    Product selectByPrimaryKey(Integer id);

    int updateByExample(@Param("record") Product record, @Param("example") ProductExample example);

    int updateByPrimaryKey(Product record);
    //获取通过搜索信息获取所有的分页数据
	List<Product> findAllProBySearchInfo(@Param("productName") String productName,@Param("ids") int[] ids);
	
	int insertSelective(Product record);
	//修改产品
	int updateProduct(Product product);
	//删除产品
	int updateByPrimaryKeySelective(Product record);
	//批量删除产品
	int updateByExampleSelective(@Param("record") Product record, @Param("example") ProductExample example);
	//根据产品类型id找到所有对应的产品
	List<Product> selectProductsByProTypeId(Integer id);
	//删除某一类型的产品
	void deleteProductByProTypeId(Integer id);
	//根据产品id找到所对应的可删除的产品订单
	Product selectProductById(Integer id);
	//删除产品
	void deleteProductById(Integer id);
	//根据产品id找到所对应的可删除的产品订单
	List<Product> selectProductsByIds(@Param("ids") int[] ids);
	//批量删除产品
	void deleteProductsByIds(@Param("ids") int[] ids);
}