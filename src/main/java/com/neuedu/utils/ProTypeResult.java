package com.neuedu.utils;

import java.util.Date;

import com.neuedu.pojo.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProTypeResult {
	private Integer pageNum;
	private Integer pageSize;
	private Long total;
	private Object  list;
}
