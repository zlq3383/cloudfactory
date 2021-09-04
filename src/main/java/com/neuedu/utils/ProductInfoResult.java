package com.neuedu.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 
* @author 作者 : 赵灵巧
* @version 创建时间：2021年8月30日 上午11:32:25 
*/
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductInfoResult {
	private Integer pageNum;
	private Integer pageSize;
	private Long total;
	private Object  list;
}
