package com.neuedu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neuedu.mapper.ProductMapper;
import com.neuedu.mapper.ProductOrderMapper;
import com.neuedu.pojo.Product;
import com.neuedu.service.ProductService;
import com.neuedu.utils.FactoryResult;
import com.neuedu.utils.ProductInfoResult;
import com.neuedu.utils.SearchProInfo;

/**
 * @author 赵灵巧
 * @create 2021-08-30 11:16
 */

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	@Autowired(required = false)
	private ProductMapper proMapper;
	private ProductOrderMapper productOrderMapper;
	//获取通过搜索信息获取所有的分页数据
    @Override
    public FactoryResult findAllProBySearchInfo(SearchProInfo info) {
    	try {
			int pageNum=info.getPageNum();
			int pageSize= info.getPageSize();
			Page page=PageHelper.startPage(pageNum,pageSize);
			List<Product> list=proMapper.findAllProBySearchInfo(info.getProductName(),info.getSelectProList());
			PageInfo<Product> pageInfo = new PageInfo<Product>(list);
			ProductInfoResult result = new ProductInfoResult(pageNum, pageSize, pageInfo.getTotal(), pageInfo.getList());
			return FactoryResult.ok(result);
		} catch (Exception e) {
			e.printStackTrace();
			return FactoryResult.fail();
		}
    }
    //新增产品
	@Override
	public FactoryResult addProduct(Product product) {
		try {
			proMapper.insertSelective(product);
			return FactoryResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return FactoryResult.fail();
		}
	}
	@Override
	public FactoryResult editProduct(Product product) {
		try {
			proMapper.updateProduct(product);
			return FactoryResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return FactoryResult.fail();
		}
	}
	//删除产品
	@Override
	public FactoryResult deleteProduct(Integer id) {
		//首先需要判断产品可以删除，如果产品有订单，订单状态为生产中20、已完成40、已被下单50后就不能删除，否则就可以
		Product product = proMapper.selectProductById(id);
		if(product.getProductOrders().size()==0) {
			try {
				proMapper.deleteProductById(id);
				return FactoryResult.ok();
			} catch (Exception e) {
				e.printStackTrace();
				return new FactoryResult(403,"删除产品失败",null);
			}
		}
		return new FactoryResult(403, "当前产品为已经生产过、已经被下单或者正在生产中，不允许删除", null);
	}
	//批量删除产品
	@Override
	public FactoryResult deleteProList(int[] ids) {
		List<Product> products=proMapper.selectProductsByIds(ids);
		for (Product product : products) {
			if(product.getProductOrders().size()!=0) {
				return new FactoryResult(403, "选中的产品中包含产品为已经生产过、已经被下单或者正在生产中，不允许删除", null);
			}
		}
		try {
			proMapper.deleteProductsByIds(ids);
			return FactoryResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return new FactoryResult(403,"批量删除产品失败",null);
		}
	}
}
