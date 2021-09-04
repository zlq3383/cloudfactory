package com.neuedu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.neuedu.pojo.User;
import com.neuedu.service.UserService;
import com.neuedu.utils.FactoryResult;

/** 
* @author 作者 : 赵灵巧
* @version 创建时间：2021年9月2日 上午10:17:06 
*/
@RestController
@CrossOrigin
public class UserController {
	@Autowired(required = false)
	private UserService userService;
	
	@PostMapping("/getUserPermitsById")
	public FactoryResult getUserPermitsById(@RequestBody User user) {
		return userService.getUserPermitsById(user.getId());
	}
}
