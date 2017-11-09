package com.dwring.springboot.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.dwring.springboot.mapper.UserInfoMapper;
import com.dwring.springboot.model.UserInfo;

@Service
@CacheConfig(cacheNames = "userInfo")
public class UserInfoService {

	@Autowired
	private UserInfoMapper userInfoMapper;

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public void save(UserInfo userInfo) {
		userInfoMapper.insert(userInfo);
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public int delete(Integer id) {
		return userInfoMapper.deleteByPrimaryKey(id);
	}

	@Cacheable(key = "#p0")
	public UserInfo getById(Integer id) {
		return userInfoMapper.selectByPrimaryKey(id);
	}

	public List<UserInfo> listUserInfos() {
		return userInfoMapper.selectAll();
	}
}
