package com.qf.springboot.controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Title: HomeController.java
 * @Package com.qf.springboot.controller
 * @Description: TODO
 * @author haichangzhang
 * @date 2017年7月19日 下午3:30:42
 * @version V1.0
 */
@Controller
public class HomeController {

	@RequestMapping({ "/", "/index" })
	public String index() {
		return "/index";
	}

	@RequestMapping(value = "/login")
	public String loginForm(HttpServletRequest request, Map<String, Object> map) {
		System.out.println("HomeController.login()");
		// 登录失败从request中获取shiro处理的异常信息。
		// shiroLoginFailure:就是shiro异常类的全类名.
		String exception = (String) request.getAttribute("shiroLoginFailure");
		System.out.println("exception=" + exception);
		String msg = "";
		if (exception != null) {
			if (UnknownAccountException.class.getName().equals(exception)) {
				System.out.println("UnknownAccountException -- > 账号不存在：");
				msg = "UnknownAccountException -- > 账号不存在：";
			} else if (IncorrectCredentialsException.class.getName().equals(exception)) {
				System.out.println("IncorrectCredentialsException -- > 密码不正确：");
				msg = "IncorrectCredentialsException -- > 密码不正确：";
			} else if ("kaptchaValidateFailed".equals(exception)) {
				System.out.println("kaptchaValidateFailed -- > 验证码错误");
				msg = "kaptchaValidateFailed -- > 验证码错误";
			} else {
				msg = "else >> " + exception;
				System.out.println("else -- >" + exception);
			}
		}
		map.put("msg", msg);
		// 此方法不处理登录成功,由shiro进行处理
		return "/login";
	}

	@RequestMapping("/403")
	public String unauthorizedRole() {
		return "403";
	}
}
