package com.qf.springboot.hazelcast.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	@RequestMapping(name="/login",method=RequestMethod.POST)
	public String login(){
		System.out.println("success");
		return "success";
	}

}
