package com.dwring.springboot.exception;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.dwring.springboot.constant.EnumUtils;
import com.dwring.springboot.model.BaseResponse;

@RestController
@ControllerAdvice
public class GlobalExcetionHandler {

	private static final Logger log = LoggerFactory.getLogger(GlobalExcetionHandler.class);

	@ExceptionHandler(value=BaseException.class)
	@ResponseBody
	public Object baseErrorHandler(HttpServletRequest request, Exception e)  throws Exception{
		log.error("default exception handler host:{} invokes url:{} ERROR:{}", request.getRemoteHost(),
				request.getRequestURI(), e.getMessage());
		BaseResponse<Object> response = new BaseResponse<Object>();
		response.setMsg(e.getLocalizedMessage());
		response.setCode(EnumUtils.ResponseCode.FAIL.getCode());
		return response;
	}

	@ExceptionHandler(value=Exception.class)
	@ResponseBody
	public Object defaultErrorHandler(HttpServletRequest request, Exception e)  throws Exception{
		log.error("default exception handler host:{} invokes url:{} ERROR:{}", request.getRemoteHost(),
				request.getRequestURI(), e.getMessage());
		BaseResponse<Object> response = new BaseResponse<Object>();
		response.setCode(EnumUtils.ResponseCode.FAIL.getCode());
		response.setMsg(e.getLocalizedMessage());
		return response;
	}
	
	@ExceptionHandler(value=BizException.class)
	@ResponseBody
	public Object bizServiceErrorHandler(HttpServletRequest request, Exception e)  throws Exception{
		log.error("default exception handler host:{} invokes url:{} ERROR:{}", request.getRemoteHost(),
				request.getRequestURI(), e.getMessage());
		BaseResponse<Object> response = new BaseResponse<Object>();
		response.setMsg(e.getLocalizedMessage());
		response.setCode(EnumUtils.ResponseCode.FAIL.getCode());
		return response;
	}
	
	
}
