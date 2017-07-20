package com.qf.springboot.service;

import com.qf.springboot.model.User;
import com.qf.springboot.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @ClassName: UserInfoService
 * @Description:
 * @author haichangzhang
 * @date 2017年6月8日 下午1:59:38
 * 
 */
@Service
public class UserService {

	@Autowired
	private UserRepository userInfoRepository;

	@Cacheable(key="#p0.id")
	public User loadById(Integer id) {
		return userInfoRepository.selectByPrimaryKey(id);
	}

	public List<User> selectAll() {
		return userInfoRepository.findAll();
	}
	@Cacheable(key="user")
	public User findByUsername(String name) {
		return userInfoRepository.findByName(name);
	}
}
