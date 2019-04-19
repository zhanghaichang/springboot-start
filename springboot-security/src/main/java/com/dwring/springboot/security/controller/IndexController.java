package com.dwring.springboot.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dwring.springboot.security.domain.BaseResponse;

@RequestMapping("/index")
@RestController
public class IndexController {

	@GetMapping("/hello")
	public BaseResponse<Object> hello() {
		return new BaseResponse<Object>(200,"normal role");
	}

	@PreAuthorize("hasAuthority('admin')")
	@GetMapping("/admin")
	public BaseResponse<Object> admin() {
		return new BaseResponse<Object>(200,"admin role");
	}
}
