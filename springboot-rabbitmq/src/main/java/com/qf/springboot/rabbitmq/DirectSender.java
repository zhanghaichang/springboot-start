package com.qf.springboot.rabbitmq;

import java.util.Date;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class DirectSender {

	@Autowired
	private AmqpTemplate rabbitTemplate;
	
	public void send(){
		String sendMsg="hello"+new Date();
		System.out.println("Direct Sender: "+sendMsg);
		rabbitTemplate.convertAndSend("directExchange","spring-boot-routingKeyA", sendMsg);
	}

}
