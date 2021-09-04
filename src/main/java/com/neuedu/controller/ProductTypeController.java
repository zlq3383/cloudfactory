package com.neuedu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.neuedu.pojo.ProductType;
import com.neuedu.service.ProductTypeService;
import com.neuedu.utils.FactoryResult;
import com.neuedu.utils.SearchProTypeInfo;
/**
 * @author 赵灵巧
 * @create 2021-08-30 10:10
 */

@RestController
@CrossOrigin
public class ProductTypeController {
	@Autowired
	private ProductTypeService proTypeService;
	
	//获取通过搜索信息获取所有的分页数据
	@PostMapping("/findProTypeList")
	public FactoryResult findProTypeList(@RequestBody SearchProTypeInfo searchProType) {
		System.out.println(searchProType);
		return proTypeService.findAllProTypeByParentId(searchProType);
	}
	
	//获取所有分类及其子类
	@PostMapping("/findProTypeAndChildren")
	public FactoryResult findProTypeAndChildren() {
		return proTypeService.findProTypeAndChildren();
	}
	
	@PostMapping("/findProTypeAndChildrenValid")
	public FactoryResult findProTypeAndChildrenValid() {
		return proTypeService.findProTypeAndChildrenValid();
	}
	
	//根据id获得产品类型
	@PostMapping("/findProTypeById")
	public FactoryResult findProTypeById(@RequestBody Integer id) {
		return proTypeService.findProTypeById(id);
	}
	
	//获取一级产品分类
	@PostMapping("/findOneClassProType")
	public FactoryResult findOneClassProType() {
		return proTypeService.findOneClassProType();
	}
	
	@PostMapping("/findOneClassProTypeValid")
	public FactoryResult findOneClassProTypeValid() {
		return proTypeService.findOneClassProTypeValid();
	}
	
	//根据产品类型信息修改数据
	@PostMapping("/updateProType")
	public FactoryResult updateProType(@RequestBody ProductType productType) {
		return proTypeService.updateProType(productType);
	}
	
	//批量新增产品分类信息
	@PostMapping("/addProTypeList")
	public FactoryResult addProTypeList(@RequestBody List<ProductType> list) {
		return proTypeService.addProTypeList(list);
	}
	
	//删除产品类别
	@PostMapping("/deleteProType")
	public FactoryResult deleteProType(@RequestBody ProductType productType) {
		return proTypeService.deleteProType(productType.getId());
	}
	
	//批量删除产品类别
	@PostMapping("/deleteProTypeList")
	public FactoryResult deleteProTypeList(@RequestBody int[] ids) {
		return proTypeService.deleteProTypes(ids);
	}
}
