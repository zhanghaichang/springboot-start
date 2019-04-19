package com.dwring.springboot.security.exception;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.dwring.springboot.security.domain.*;
import com.dwring.springboot.security.constant.EnumUtils;
import lombok.extern.slf4j.Slf4j;

/**   
 * @ClassName:  GlobalExceptionHandler   
 * @Description:全局异常处理   
 * @author: zhanghaichang
 * @date:   2019年4月10日 下午3:20:53   
 *     
 * @Copyright: 2019 www.topcheer.com Inc. All rights reserved. 
 * 注意：本内容仅限于上海天正软件公司内部传阅，禁止外泄以及用于其他的商业目 
 */
@RestController
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	/**   
	 * @Title: baseErrorHandler   
	 * @Description: 基础异常   
	 * @param: @param request
	 * @param: @param e
	 * @param: @return      
	 * @return: Object      
	 * @throws   
	 */
	@ExceptionHandler(value = BaseException.class)
	@ResponseBody
	public Object baseErrorHandler(HttpServletRequest request, Exception e) {
		log.error("base exception handler host:{} invokes url:{} ERROR:{}", request.getRemoteHost(),
				request.getRequestURI(), e.getMessage());
		BaseResponse<Object> response=new BaseResponse<Object>();
		response.setCode(EnumUtils.ResponseCode.FAIL.getCode());
		response.setMsg(e.getLocalizedMessage());
		return response;
		
	}
	
	
	/**   
	 * @Title: defaultErrorHandler   
	 * @Description: 默认异常   
	 * @param: @param request
	 * @param: @param e
	 * @param: @return      
	 * @return: Object      
	 * @throws   
	 */
	@ExceptionHandler(value =Exception.class)
	@ResponseBody
	public Object defaultErrorHandler(HttpServletRequest request, Exception e) {
		log.error("default exception handler host:{} invokes url:{} ERROR:{}", request.getRemoteHost(),
				request.getRequestURI(), e.getMessage());
		BaseResponse<Object> response =new BaseResponse<Object>();
		response.setCode(EnumUtils.ResponseCode.FAIL.getCode());
		response.setMsg(e.getLocalizedMessage());
		return response;
	}

	/**   
	 * @Title: bizServiceErrorHandler   
	 * @Description: 业务异常  
	 * @param: @param request
	 * @param: @param e
	 * @param: @return      
	 * @return: Object      
	 * @throws   
	 */
	@ExceptionHandler(value = BizException.class)
	@ResponseBody
	public Object  bizServiceErrorHandler(HttpServletRequest request,Exception e) {
		log.error("biz exception handler host:{} invokes url:{} ERROR:{}", request.getRemoteHost(),
				request.getRequestURI(), e.getMessage());
		BaseResponse<Object> response=new BaseResponse<Object>();
		response.setCode(EnumUtils.ResponseCode.FAIL.getCode());
		response.setMsg(e.getLocalizedMessage());
		return response;
	}

}
