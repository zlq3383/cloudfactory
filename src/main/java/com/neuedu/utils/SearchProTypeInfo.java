package com.neuedu.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SearchProTypeInfo {
	private Integer pageNum;
	private Integer pageSize;
	private int[]  selectProTypeList;
}
