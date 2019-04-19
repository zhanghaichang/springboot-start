package com.dwring.springboot.security.vo;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

@Data
public class UserResponse {

	@JSONField(name = "user_id")
	private Long id;

	@JSONField(name = "access_token")
	private String accessToken;

}
