package com.qf.application.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ConsumerController {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@RequestMapping("/kafka")
	@ResponseBody
	public String loadMsg() {
		kafkaTemplate.send("test1", "zhanghaichang2019", "hello world.");
		return "Successfully";
	}
}
