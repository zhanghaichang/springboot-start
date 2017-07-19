package com.dwring.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dwring.springboot.dao.UserDao;
import com.dwring.springboot.domain.User;

/**
 * Created by zhanghaichang on 2017/4/9.
 */
@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	public void save(User user) {
		userDao.save(user);
	}

	public User find(User user) {
		return userDao.find(user);
	}

	public void delete(User user) {
		userDao.delete(user);
	}
	
	public void update(User user){
		userDao.update(user);
	}
	public List<User> findAll() {
		return userDao.findAll();
	}

}
