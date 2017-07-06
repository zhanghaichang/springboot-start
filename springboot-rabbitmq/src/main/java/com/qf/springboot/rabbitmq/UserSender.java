package com.qf.springboot.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qf.springboot.domain.User;

@Component
public class UserSender {

	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public void send(){
		User user=new User();
		user.setUsername("zhanghaichang");
		user.setId(1);
		user.setPassword("admin");
		this.rabbitTemplate.convertAndSend("user", user);
	}
}
