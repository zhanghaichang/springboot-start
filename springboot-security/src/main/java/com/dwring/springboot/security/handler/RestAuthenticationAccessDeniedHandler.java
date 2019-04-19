package com.dwring.springboot.security.handler;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSONObject;
import com.dwring.springboot.security.constant.EnumUtils;
import com.dwring.springboot.security.domain.BaseResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * 权限不足处理类，返回403 Author: JoeTao createAt: 2018/9/21
 */
@Slf4j
@Component("RestAuthenticationAccessDeniedHandler")
public class RestAuthenticationAccessDeniedHandler implements AccessDeniedHandler {
	@Override
	public void handle(HttpServletRequest httpServletRequest, HttpServletResponse response, AccessDeniedException e)
			throws IOException, ServletException {
		// 登陆状态下，权限不足执行该方法
		log.info("权限不足：{}", e.getMessage());
		response.setStatus(200);
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter printWriter = response.getWriter();
		BaseResponse<Object> responseBody=new BaseResponse<Object>();
		responseBody.setCode(EnumUtils.ResponseCode.FAIL.getCode());
		responseBody.setMsg("权限不足");
		printWriter.write(JSONObject.toJSONString(responseBody));
		printWriter.flush();
	}
}