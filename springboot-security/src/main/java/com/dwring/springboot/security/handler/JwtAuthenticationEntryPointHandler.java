package com.dwring.springboot.security.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.dwring.springboot.security.constant.EnumUtils;
import com.dwring.springboot.security.domain.BaseResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * 认证失败处理类，返回401 Author: JoeTao createAt: 2018/9/20
 */
@Slf4j
@Component
public class JwtAuthenticationEntryPointHandler implements AuthenticationEntryPoint, Serializable {

	private static final long serialVersionUID = -8970718410437077606L;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {
		// 验证为未登陆状态会进入此方法，认证错误
		log.info("认证失败：{}",authException.getMessage());
		response.setStatus(200);
        response.setHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter printWriter = response.getWriter();
		BaseResponse<Object> responseBody=new BaseResponse<Object>();
		responseBody.setCode(EnumUtils.ResponseCode.FAIL.getCode());
		responseBody.setMsg("认证失败");
		printWriter.write(JSONObject.toJSONString(responseBody));
		printWriter.flush();
	}
}