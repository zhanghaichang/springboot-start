package com.dwring.springboot.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
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

	/**
	 * 查询结果缓存到缓存中
	 * 
	 * @param id
	 * @return
	 */
	@Cacheable(key = "#p0")
	public UserInfo getById(Integer id) {
		return userInfoMapper.selectByPrimaryKey(id);
	}

	/**
	 * 清空UserInfo缓存
	 * 
	 * @param id
	 * @return
	 */
	@CacheEvict(key = "#p0", allEntries = true)
	public int delById(Integer id) {
		return userInfoMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 指定key，将更新的结果同步到缓存中
	 * 
	 * @return
	 */
	 @CachePut(key = "#p0.id")
	public UserInfo updateById(UserInfo userInfo) {
		userInfoMapper.updateByPrimaryKey(userInfo);
		return userInfoMapper.selectByPrimaryKey(userInfo.getId());
	}

	public List<UserInfo> listUserInfos() {
		return userInfoMapper.selectAll();
	}
}
