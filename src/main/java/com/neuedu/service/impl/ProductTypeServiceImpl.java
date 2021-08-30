package com.neuedu.service.impl;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neuedu.mapper.ProductTypeMapper;
import com.neuedu.pojo.ProductExample;
import com.neuedu.pojo.ProductType;
import com.neuedu.pojo.ProductTypeExample;
import com.neuedu.pojo.ProductTypeExample.Criteria;
import com.neuedu.service.ProductTypeService;
import com.neuedu.utils.FactoryResult;
import com.neuedu.utils.ProTypeResult;
import com.neuedu.utils.SearchProTypeInfo;

@Service
@Transactional
public class ProductTypeServiceImpl implements ProductTypeService {
	@Autowired(required = false)
	private ProductTypeMapper proTypeMapper;
	//获取通过id数组获取所有的分页数据
	@Override
	public FactoryResult findAllProTypeByParentId(SearchProTypeInfo info) {
		try {
			int pageNum=info.getPageNum();
			int pageSize= info.getPageSize();
			Page page=PageHelper.startPage(pageNum,pageSize);
			List<ProductType> list=proTypeMapper.findAllProTypeByIds(info.getSelectProTypeList());
			PageInfo<ProductType> pageInfo = new PageInfo<ProductType>(list);
			List<ProductType> resList=new ArrayList<ProductType>();
			for (ProductType productType : pageInfo.getList()) {
				if(productType.getChildren().size()==0) {
					productType.setChildren(null);
				}
				resList.add(productType);
			}
			ProTypeResult result=new ProTypeResult(pageNum, pageSize, pageInfo.getTotal(), resList);
			return FactoryResult.ok(result);
		} catch (Exception e) {
			e.printStackTrace();
			return FactoryResult.fail();
		}
	}
	//获取所有分类及其子类
	@Override
	public FactoryResult findProTypeAndChildren() {
		List<ProductType> list = proTypeMapper.findProTypeAndChildren();
		List<ProductType> res = new ArrayList<ProductType>();
		for (ProductType productType : list) {
			if(productType.getChildren().size()==0) {
				productType.setChildren(null);
			}
			res.add(productType);
		}
		return FactoryResult.ok(res);
	}
	//根据id获得产品类型
	@Override
	public FactoryResult findProTypeById(Integer id) {
		ProductType productType = proTypeMapper.selectByPrimaryKey(id);
		return FactoryResult.ok(productType);
	}
	//获取一级产品分类
	@Override
	public FactoryResult findOneClassProType() {
		List<ProductType> productType = proTypeMapper.findOneClassProType();
		return FactoryResult.ok(productType);
	}
	//根据产品类型信息修改产品数据
	@Override
	public FactoryResult updateProType(ProductType productType) {
		
		try {
			proTypeMapper.updateProType(productType);
			return FactoryResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return FactoryResult.fail();
		}
	}
	//批量新增产品分类信息
	@Override
	public FactoryResult addProTypeList(List<ProductType> list) {
		try {
			proTypeMapper.addProTypeList(list);
			return FactoryResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return FactoryResult.fail();
		}
	}
	//删除产品类别信息
	@Override
	public FactoryResult deleteProType(Integer id) {
		try {
			ProductType proType=new ProductType();
			proType.setId(id);
			proType.setFlag(1);
			proTypeMapper.updateByPrimaryKeySelective(proType);
			return FactoryResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return FactoryResult.fail();
		}
	}
	//批量删除产品类别
	@Override
	public FactoryResult deleteProType(List<Integer> ids) {
		try {
			ProductTypeExample example=new ProductTypeExample();
			Criteria criteria = example.createCriteria();
			criteria.andIdIn(ids);
			ProductType proType=new ProductType();
			proType.setFlag(1);
			proTypeMapper.updateByExampleSelective(proType, example);
			return FactoryResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return FactoryResult.fail();
		}
	}
	

}
