package com.neuedu.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neuedu.mapper.ProductMapper;
import com.neuedu.mapper.ProductTypeMapper;
import com.neuedu.pojo.Product;
import com.neuedu.pojo.ProductType;
import com.neuedu.service.ProductTypeService;
import com.neuedu.utils.FactoryResult;
import com.neuedu.utils.ProTypeResult;
import com.neuedu.utils.SearchProTypeInfo;

@Service
@Transactional
public class ProductTypeServiceImpl implements ProductTypeService {
	@Autowired(required = false)
	private ProductTypeMapper proTypeMapper;
	@Autowired(required = false)
	private ProductMapper productMapper;
	
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
	
	//获取所有分类及其子类,包括无效的
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
	
	//获取所有分类以及子类，只包括有效的
	@Override
	public FactoryResult findProTypeAndChildrenValid() {
		List<ProductType> list = proTypeMapper.findProTypeAndChildrenValid();
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
	
	//获取一级产品分类，只包括有效的
	@Override
	public FactoryResult findOneClassProTypeValid() {
		List<ProductType> productType = proTypeMapper.findOneClassProTypeValid();
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
		ProductType productType=proTypeMapper.selectProOrdersById(id);
		List<ProductType> children = productType.getChildren();
		if(children.size()==0) {
			int flag=deleteProTypeWithoutChildren(productType);
			if(flag==10) {
				//如果类别都可以删除，就删除产品类型以及类型下的产品
				try {
					proTypeMapper.deleteProType(id);
					productMapper.deleteProductByProTypeId(id);
					return FactoryResult.ok();
				} catch (Exception e) {
					e.printStackTrace();
					return new FactoryResult(403, "删除产品类型失败", null);
				}
			}else {
				return new FactoryResult(403, "当前产品类型下有产品不允许删除，不能删除产品类型", null);
			}
		}else {
			//如果有子类,遍历子类
			for (ProductType child  : children) {
				//判断能否删除子类，一个不能删除，产品类型就不能删除
				int flag=deleteProTypeWithoutChildren(child);
				if(flag==20) {
					return new FactoryResult(403, "当前产品类型下有子类型不允许删除，不能删除产品类型", null);
				}
			}
			try {
				//如果子类别都可以删除，就删除产品类型以及子类以及类型下的产品
				proTypeMapper.deleteProType(id);
				proTypeMapper.deleteProTypeChildren(id);
				for (ProductType child : children) {
					productMapper.deleteProductByProTypeId(child.getId());
				}
				return FactoryResult.ok();
			} catch (Exception e) {
				e.printStackTrace();
				return new FactoryResult(403, "删除产品类型失败", null);
			}
		}
	}
	
	//无子类的产品类别的删除，10可以删除，20不能删除
	public Integer deleteProTypeWithoutChildren(ProductType productType) {
		//如果没有子类
		List<Product> products=productType.getProducts();
		if(products.size()==0) {
			//如果没有产品，可以删除
			return 10;
		}else {
			//如果有产品，遍历产品数组，如果所有产品都可以删除，则删除此产品类别，否则不可以
			for (Product product : products) {
				if(product.getProductOrders().size()!=0) {
					return 20;
				}
			}
			return 10;
		}
	}
	
	//批量删除产品类别
	@Override
	public FactoryResult deleteProTypes(int[] ids) {
		List<ProductType>  productTypes=proTypeMapper.selectProOrdersByIds(ids);
		for (ProductType productType : productTypes) {
			List<ProductType> children = productType.getChildren();
			if(children.size()==0) {
				int flag=deleteProTypeWithoutChildren(productType);
				if(flag==20) {
					return new FactoryResult(403, "当前产品类型下有产品不允许删除，不能删除产品类型", null);
				}
			}else {
				//如果有子类,遍历子类
				for (ProductType child  : children) {
					//判断能否删除子类，一个不能删除，产品类型就不能删除
					int flag=deleteProTypeWithoutChildren(child);
					if(flag==20) {
						return new FactoryResult(403, "当前产品类型下有子产品类型不允许删除，不能删除产品类型", null);
					}
				}
			}
		}
		try {
			for (int id  : ids) {
				ProductType productType=proTypeMapper.selectProOrdersById(id);
				List<ProductType> children = productType.getChildren();
				if(children.size()==0) {
						proTypeMapper.deleteProType(id);
						productMapper.deleteProductByProTypeId(id);
				}else {
					
						//如果子类别都可以删除，就删除产品类型以及子类以及类型下的产品
						proTypeMapper.deleteProType(id);
						proTypeMapper.deleteProTypeChildren(id);
						for (ProductType child : children) {
							productMapper.deleteProductByProTypeId(child.getId());
						}
				}
			}
			return FactoryResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return new FactoryResult(403, "批量删除产品类型失败", null);
		}
	}
}
