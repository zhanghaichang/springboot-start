package com.qf.springboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qf.springboot.mapper.UserInfoMapper;
import com.qf.springboot.model.UserInfo;
import com.qf.springboot.service.IUserInfoService;

/**
 * @Title: UserInfoServiceImpl.java
 * @Package com.qf.springboot.service.impl
 * @Description: TODO
 * @author haichangzhang
 * @date 2017年7月11日 下午6:23:35
 * @version V1.0
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

	@Autowired
	private UserInfoMapper userInfoMapper;

	public List<UserInfo> getAll() {
		return userInfoMapper.getAll();
	}
}
