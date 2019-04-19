package com.dwring.springboot.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.dwring.springboot.security.domain.BaseResponse;
import com.dwring.springboot.security.service.UserService;
import com.dwring.springboot.security.vo.UserRequest;
import com.dwring.springboot.security.vo.UserResponse;

@RestController
public class LoginController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/api/login", method = RequestMethod.POST)
	public BaseResponse<UserResponse> login(@RequestBody UserRequest user, Device device) {
		return new BaseResponse<UserResponse>(userService.login(user.getUsername(), user.getPassword(), device), 200);
	}
}
