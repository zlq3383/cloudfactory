package com.neuedu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neuedu.mapper.UserMapper;
import com.neuedu.pojo.User;
import com.neuedu.service.UserService;
import com.neuedu.utils.FactoryResult;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper userMapper;
	@Override
	public FactoryResult login(User user) {
		User tuser=userMapper.login(user);
		if(tuser==null) return FactoryResult.fail();
		else return FactoryResult.ok(tuser);
	}
}
