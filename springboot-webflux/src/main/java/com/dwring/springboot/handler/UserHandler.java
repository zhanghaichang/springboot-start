package com.dwring.springboot.handler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dwring.springboot.dao.UserInfoRepository;
import com.dwring.springboot.model.UserInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserHandler {

	private final UserInfoRepository userRepository;

	@Autowired
	public UserHandler(UserInfoRepository userRepository) {
		this.userRepository = userRepository;
	}

	public Long save(UserInfo user) {
		return userRepository.save(user);
	}

	public UserInfo findUserById(Long id) {
		return userRepository.getUserById(id);
	}

	public List<UserInfo> findAll() {
		log.info("-------findAll----------");
		return userRepository.findAll().stream().collect(Collectors.toList());
	}

	public Long update(UserInfo user) {
		return userRepository.updateUser(user);
	}

	public Long delete(Long id) {
		return userRepository.delete(id);
	}
}
