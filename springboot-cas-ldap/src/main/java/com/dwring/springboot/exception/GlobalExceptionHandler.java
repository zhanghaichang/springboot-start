package com.dwring.springboot.exception;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Title: GlobaException.java
 * @Package com.qf.com.qf.springboot.exception
 * @Description: 全局异常处理
 * @author haichangzhang
 * @date 2017年7月7日 下午2:56:47
 * @version V1.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	/** 
	* @Title: defaultException 
	* @Description: 默认异常处理
	* @param @param request
	* @param @param ex
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@ExceptionHandler(value = Exception.class)
	public String defaultException(HttpServletRequest request, Exception ex) {
		logger.error("reqeust url:{}", request.getRequestURI());
		logger.error("global exception:{}", ex.getMessage());
		return "{\"code\":\"400\",\"msg\":\""+ex.getMessage()+"\"}";
	}
}
