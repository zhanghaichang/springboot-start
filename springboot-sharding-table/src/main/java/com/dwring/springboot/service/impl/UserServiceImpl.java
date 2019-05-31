package com.dwring.springboot.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dwring.springboot.mapper.UserMapper;
import com.dwring.springboot.model.User;
import com.dwring.springboot.service.UserService;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

	@Override
	public boolean save(User entity) {
		return super.save(entity);
	}

	@Override
	public List<User> getUserList() {
		return baseMapper.selectList(Wrappers.<User>lambdaQuery());
	}
}
