package com.neuedu.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neuedu.mapper.UserMapper;
import com.neuedu.pojo.Permit;
import com.neuedu.pojo.User;
import com.neuedu.service.UserService;
import com.neuedu.utils.FactoryResult;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper userMapper;
	//登录
	@Override
	public FactoryResult login(User user) {
		User tuser=userMapper.login(user);
		if(tuser==null) return FactoryResult.fail();
		else return FactoryResult.ok(tuser);
	}
	//获取权限信息
	@Override
	public FactoryResult getUserPermitsById(Integer id) {
		try {
			User user=userMapper.getUserPermitsById(id);
			List<Permit> permits=user.getPermits();
			List<Permit> resList=new ArrayList<>();
			//System.err.println(permits);
			for (Permit permit : permits) {
				List<Permit> children=new ArrayList<>();
				if(permit.getParentId()==null){
					for(Permit permit2:permits) {
						if(permit2.getParentId()==permit.getId()) {
							children.add(permit2);
						}
					}
					permit.setChildren(children);
					resList.add(permit);
				}
			}
			user.setPermits(resList);
			return FactoryResult.ok(user);
		} catch (Exception e) {
			e.printStackTrace();
			return FactoryResult.fail();
		}
	}
	
}
