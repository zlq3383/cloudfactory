package com.neuedu.service;

import com.neuedu.pojo.Product;
import com.neuedu.utils.FactoryResult;
import com.neuedu.utils.SearchProInfo;

/**
 * @author 赵灵巧
 * @create 2021-08-30 11:10
 */
public interface ProductService {
    FactoryResult findAllProBySearchInfo(SearchProInfo searchProInfo);

	FactoryResult addProduct(Product product);

	FactoryResult editProduct(Product product);

	FactoryResult deleteProduct(Integer id);

	FactoryResult deleteProList(int[] ids);
}
