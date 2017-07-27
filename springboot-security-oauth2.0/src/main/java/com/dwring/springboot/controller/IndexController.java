package com.dwring.springboot.controller;

import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Title: IndexController.java
 * @Package com.dwring.springboot.controller
 * @Description: OAuth 2.0 认证的原理与实践（github第三方登录）
 * @author haichangzhang
 * @date 2017年7月27日 上午10:25:34
 * @version V1.0
 */
@Controller
public class IndexController {

	@GetMapping("/")
	public String root() {
		return "redirect:/index";
	}

	@GetMapping("/index")
	public String index(Principal principal, Model model) {
		if (principal == null) {
			return "index";
		}
		model.addAttribute("principal", principal);
		return "index";
	}

	@GetMapping("/403")
	public String accessDenied() {
		return "403";
	}
}
