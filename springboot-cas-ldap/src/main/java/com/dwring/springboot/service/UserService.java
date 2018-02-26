package com.dwring.springboot.service;

import com.dwring.springboot.model.User;
import com.dwring.springboot.repository.UserRepository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
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

	public User loadById(Integer id) {
		return userInfoRepository.selectByPrimaryKey(id);
	}

	public List<User> selectAll() {
		return userInfoRepository.findAll();
	}


}
