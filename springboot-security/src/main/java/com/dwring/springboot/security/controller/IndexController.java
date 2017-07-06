package com.dwring.springboot.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping(name="/")
	public String index(){
		return "index";
	}
	
	 @RequestMapping("/hello")
	    public String hello() {
	        return "hello";
	    }
}
