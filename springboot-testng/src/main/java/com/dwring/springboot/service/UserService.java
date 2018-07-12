package com.dwring.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dwring.springboot.model.UserInfo;
import com.dwring.springboot.repository.UserRepository;

/**
 * @Title: UserService.java
 * @Package com.dwring.springboot.service
 * @Description: TODO
 * @author haichangzhang
 * @date 2018年7月11日 下午6:32:38
 * @version V1.0
 */
@Service
public class UserService {

	@Autowired
	private UserRepository userDao;

	public List<UserInfo> queryAll() {
		return userDao.selectAll();
	}

}
