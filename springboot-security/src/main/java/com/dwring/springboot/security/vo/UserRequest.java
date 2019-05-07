package com.dwring.springboot.security.vo;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

@Data
public class UserRequest {

	@JSONField(name = "user_name")
	private String username;

	private String password;
	
	private String imeiNo;

}
