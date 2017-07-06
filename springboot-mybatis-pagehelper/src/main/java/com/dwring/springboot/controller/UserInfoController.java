package com.dwring.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dwring.springboot.model.UserInfo;
import com.dwring.springboot.service.UserInfoService;

@RestController
@RequestMapping("/users")
public class UserInfoController {

	@Autowired
	private UserInfoService userInfoService;

	@RequestMapping(value = "/view/{id}")
	public UserInfo view(@PathVariable Integer id) {
		UserInfo userInfo = userInfoService.getById(id);
		return userInfo;
	}
}
