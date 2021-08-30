package com.neuedu.controller;

import java.io.Console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.neuedu.pojo.User;
import com.neuedu.service.UserService;
import com.neuedu.utils.FactoryResult;

@RestController
@CrossOrigin
public class LoginController {
	@Autowired
	private UserService userService;
	@PostMapping("/login")
	public FactoryResult login(@RequestBody User user) {
		FactoryResult result=userService.login(user);
		return result;
		
	}
}
