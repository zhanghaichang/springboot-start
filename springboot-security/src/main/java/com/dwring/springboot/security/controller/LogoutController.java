package com.dwring.springboot.security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dwring.springboot.security.domain.BaseResponse;

@RestController
public class LogoutController {

	@RequestMapping(value = "/api/logout", method = RequestMethod.GET)
	public BaseResponse<Object> logout() {
		return new BaseResponse<Object>();
	}
}
