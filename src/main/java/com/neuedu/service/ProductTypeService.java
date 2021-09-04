package com.neuedu.service;

import java.util.List;

import com.neuedu.pojo.ProductType;
import com.neuedu.utils.FactoryResult;
import com.neuedu.utils.SearchProTypeInfo;

public interface ProductTypeService {


	FactoryResult findAllProTypeByParentId(SearchProTypeInfo info);

	FactoryResult findProTypeAndChildren();

	FactoryResult findProTypeById(Integer id);

	FactoryResult updateProType(ProductType productType);

	FactoryResult findOneClassProType();

	FactoryResult addProTypeList(List<ProductType> list);

	FactoryResult deleteProType(Integer id);

	FactoryResult deleteProTypes(int[] ids);

	FactoryResult findProTypeAndChildrenValid();

	FactoryResult findOneClassProTypeValid();


}
